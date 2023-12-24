package sulic.web.alumnidatamanagement.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import sulic.web.alumnidatamanagement.common.HashUtil
import sulic.web.alumnidatamanagement.entity.Alumni
import sulic.web.alumnidatamanagement.entity.common.College
import sulic.web.alumnidatamanagement.entity.common.Gender
import sulic.web.alumnidatamanagement.entity.common.Role
import java.io.Serializable

/**
 * DTO for {@link sulic.web.alumnidatamanagement.entity.Alumni}
 */
@Schema(description = "用户注册DTO")
data class UserRegisterDto(
    @field:NotNull @field:Size(max = 15) @field:NotEmpty @field:NotBlank val number: String? = null,
    val gender: Gender? = null,
    @field:NotNull @field:Size(max = 31) @field:NotEmpty @field:NotBlank val pwd: String? = null,
    @field:Size val birthday: String? = null,
    @field:Size(max = 15) val klass: String? = null,
    @field:Size(max = 31) val college: College = College.COMPUTER_SCIENCE_AND_TECHNOLOGY,
    @field:Size(max = 31) val company: String? = null,
    @field:Size(max = 31) val phone: String? = null,
    @field:Size(max = 31) val email: String? = null,
    @field:Size(max = 31) val major: String? = null,
    @field:NotNull @field:Size(max = 6) @field:NotEmpty @field:NotBlank val imgCode: String? = null,
    @field:NotNull @field:Size(max = 31) @field:NotEmpty @field:NotBlank val name: String? = null,
) : Serializable {
    fun toEntity(): Alumni{
        val alumni = Alumni()
        alumni.apply {
            number = this@UserRegisterDto.number
            salt = HashUtil.generateSalt()
            pwdHash = HashUtil.hash(pwd!!, salt!!)
            klass = this@UserRegisterDto.klass
            company = this@UserRegisterDto.company
            college = this@UserRegisterDto.college
            phone = this@UserRegisterDto.phone
            major = this@UserRegisterDto.major
            email = this@UserRegisterDto.email
            role = Role.User
            name = this@UserRegisterDto.name
        }
        return alumni
    }
}