package sulic.web.alumnidatamanagement.controller

import cn.dev33.satoken.annotation.SaCheckRole
import cn.dev33.satoken.annotation.SaIgnore
import cn.dev33.satoken.stp.SaLoginModel
import cn.dev33.satoken.stp.StpUtil
import cn.hutool.captcha.CaptchaUtil
import cn.hutool.captcha.LineCaptcha
import cn.hutool.captcha.generator.RandomGenerator
import cn.hutool.crypto.SecureUtil
import io.swagger.v3.oas.annotations.Operation
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import sulic.projectlot.smartchargerprojectlot.exception.VerificationException
import sulic.web.alumnidatamanagement.common.HashUtil
import sulic.web.alumnidatamanagement.common.IFResult
import sulic.web.alumnidatamanagement.common.MESSAGE
import sulic.web.alumnidatamanagement.dto.AlumniInfoDto
import sulic.web.alumnidatamanagement.dto.PageableDto
import sulic.web.alumnidatamanagement.dto.UserLoginDto
import sulic.web.alumnidatamanagement.dto.UserRegisterDto
import sulic.web.alumnidatamanagement.entity.common.College
import sulic.web.alumnidatamanagement.entity.common.Gender
import sulic.web.alumnidatamanagement.entity.common.Role
import sulic.web.alumnidatamanagement.repository.AlumniRepository
import java.io.IOException
import javax.security.auth.login.LoginException

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin(origins = ["*"])
class UserController(
    private val alumniRepository: AlumniRepository
){
    private var lineCaptcha: LineCaptcha? = null
    private var log: Logger = LoggerFactory.getLogger(UserController::class.java)

    companion object{
        private val _OK = MESSAGE.ok()
    }

    @PostConstruct
    fun init(){
        val ins = UserRegisterDto(number = "admin", name = "admin", pwd = "admin", imgCode = "").toEntity()
        ins.role = Role.Admin
        log.info(alumniRepository.save(ins).toString())
    }

    @GetMapping("/verification/img_code")
    @Operation(summary = "图形验证码", description = "将此路径写进img的src")
    @SaIgnore
    @Throws(IOException::class)
    fun registerUserCode(response: HttpServletResponse) {
        val randomGenerator = RandomGenerator("0123456789", 6)
        lineCaptcha = CaptchaUtil.createLineCaptcha(100, 30)
        response.contentType = "image/jpeg"
        response.setHeader("Pragma", "No-cache")
        lineCaptcha!!.generator = randomGenerator
        lineCaptcha!!.write(response.outputStream)
        log.info(lineCaptcha!!.code)
        response.outputStream.close()
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "管理员账号: <br/> &emsp; number: admin <br/> &emsp; pwd: admin")
    @SaIgnore
    @Throws(LoginException::class)
    fun login(@Valid @RequestBody userLoginDto: UserLoginDto): MESSAGE{
        assertVerificationCode(userLoginDto.imgCode)
        log.info(userLoginDto.toString())
        val u = alumniRepository.findByNumber(userLoginDto.number!!)
        if(HashUtil.hash(userLoginDto.pwd!!, u.salt!!) != u.pwdHash)
            throw LoginException("The number ${userLoginDto.number} doesn't match the password ${userLoginDto.pwd}")
        StpUtil.login(u.id, SaLoginModel().setIsWriteHeader(true))
        return _OK
    }


    @PostMapping("/register")
    @Operation(summary = "用户注册")
    @SaIgnore
    @Throws(LoginException::class)
    fun register(@Valid @RequestBody userRegisterDto: UserRegisterDto): IFResult<AlumniInfoDto>{
        assertVerificationCode(userRegisterDto.imgCode)
        if(userRegisterDto.imgCode != lineCaptcha!!.code)
            throw VerificationException("Picture verification code error")
        val a = alumniRepository.save(userRegisterDto.toEntity())
        return IFResult.data(AlumniInfoDto.fromEntity(a), "Successfully registered")
    }

    @GetMapping("/check")
    @Operation(summary = "登录检查",
        description = "检查token是否有效")
    fun loginCheck(): MESSAGE{
        return _OK
    }

    @PostMapping
    @Operation(summary = "获取用户数据", description = "管理员获取用户数据")
    @SaCheckRole("Admin")
    fun getUserList(@Valid @RequestBody pageableDto: PageableDto): IFResult<Page<AlumniInfoDto>> =
        IFResult.data(alumniRepository.findAll(PageRequest.of(pageableDto.page, pageableDto.size)).map { AlumniInfoDto.fromEntity(it) })


    @DeleteMapping("/{id}/delete")
    @Operation(summary = "删除用户", description = "管理员删除用户")
    @SaCheckRole("Admin")
    fun deleteUser(@PathVariable id: Long): MESSAGE {
        alumniRepository.deleteById(id)
        return _OK
    }

    @GetMapping("/{id}")
    @Operation(summary = "获取用户信息")
    fun getInfo(@PathVariable id: Long): IFResult<AlumniInfoDto> = IFResult.data(AlumniInfoDto.fromEntity(alumniRepository.findById(id).get()))



    @PatchMapping("batch_modify")
    @Operation(
        summary = "批量编辑成员信息",
        description = "批量编辑成员信息, 格式如下[id] [propertyName] [newValue] <br/> " +
                "多个指令用分号隔开 <br/>" +
                "propertyName可选值：name, number, pwd, major, gender, birthday, college, company, phone, email <br/>" +
                "college可选用值：能源与环境学院、环境与化学工程学院、电气工程学院、自动化工程学院、计算机科学与技术、电子信息工程学院、经济与管理学院、数理学院外国语学院、体育学院、马克思主义学院、人文艺术学院 及其College枚举类类名 <br/>" +
                "gender可选用值：Male, Female"
    )
    @SaCheckRole("Admin")
    fun modifyBatchMember(text: String): MESSAGE {
        val sentences = text.split(";")
        val exceptions = StringBuilder()
        for (s in sentences) {
            val token = s.split("\\s++")
            if (token.size != 3) {
                exceptions.append("Syntax Error: $s;\n")
                continue
            }
            val (id, propertyName, newValue) = token
            try {
                val m = alumniRepository.findById(id.toLong()).get()
                when (propertyName) {
                    "name" -> m.name = newValue
                    "number" -> m.number = newValue
                    "pwd" -> {
                        m.salt = HashUtil.generateSalt()
                        m.pwdHash = HashUtil.hash(newValue, m.salt!!)
                    }
                    "major" -> m.major = newValue
                    "gender" -> m.gender = Gender.valueOf(newValue)
                    "birthday" -> m.birthday = newValue
                    "college" -> m.college = College.findCollegeByName(newValue)
                    "company" -> m.company = newValue
                    "phone" -> m.phone = newValue
                    "email" -> m.email = newValue
                    else -> exceptions.append("Error occurred in \"$s\": Invalid property name")
                }
            } catch (e: Exception) {
                exceptions.append("Error occurred in \"$s\": ${e.message}")
            }
        }
        return _OK
    }

    /**
     * 验证图像验证码
     */
    private fun assertVerificationCode(imgCode: String?){
        if(imgCode!! != lineCaptcha!!.code)
            throw VerificationException("Picture verification code error")
    }
}