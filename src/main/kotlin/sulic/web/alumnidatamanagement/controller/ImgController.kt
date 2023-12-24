package sulic.web.alumnidatamanagement.controller

import cn.dev33.satoken.annotation.SaCheckLogin
import cn.hutool.core.img.ImgUtil
import cn.hutool.core.io.FileUtil
import cn.hutool.core.util.RandomUtil
import io.swagger.v3.oas.annotations.Operation
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import sulic.web.alumnidatamanagement.common.IFResult
import sulic.web.alumnidatamanagement.common.MESSAGE
import java.awt.Color
import java.awt.Font
import java.io.File
import java.io.IOException

@CrossOrigin(origins = ["*"])
@RestController
@RequestMapping("api/v1/imgs")
class ImgController {
    @Value("\${web.upload-path}")
    private val uploadPath: String? = null

    @Value("\${web.host-ip}")
    private val ip: String? = null

    @Value("\${web.host-port}")
    private val port = 0

    private companion object {
        const val UNIQUE_ID_LENGTH = 6
        val ALLOWED_FILE_SUFFIX = "jpg;png".split(";").toList()
    }

    @PostConstruct
    fun init(){
        val temp = File("$uploadPath/temp")
        if(!temp.exists())
            temp.mkdirs()
    }

    @PostMapping("upload")
    @Operation(summary = "上传图像", description = "返回图像的url，图像大小不能超过5mb")
    @SaCheckLogin
    @Throws(IOException::class)
    fun uploadImg(file: MultipartFile): MESSAGE {
        val fileName = file.originalFilename
        val suffix = FileUtil.getSuffix(fileName)
        val randomUID = RandomUtil.randomString(UNIQUE_ID_LENGTH)
        val newFileName = "$randomUID.${suffix}"
        // File suffix check
        if(!ALLOWED_FILE_SUFFIX.contains(suffix)) throw SecurityException("Unallowed file")
        // File Transferring
        val rawFile = File("$uploadPath/temp/$newFileName")
        file.transferTo(rawFile)
        // File conveying
        val markedFile = FileUtil.file("$uploadPath/$newFileName")
        markedFile.createNewFile()
        val img = ImgUtil.read(rawFile)
        ImgUtil.pressText(img, FileUtil.getOutputStream(markedFile), "WATERMARK",
            Color(0.0F, 0.0F, 0.0F, 0.0F), Font("微软雅黑", Font.BOLD, 40), 0, 0, 0.8f)
        // Remove raw img File
        rawFile.delete()
        return IFResult.ok("$ip:$port/api/res/$newFileName")
    }
}
