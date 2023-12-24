package sulic.web.alumnidatamanagement.dto

import io.swagger.v3.oas.annotations.media.Schema
import sulic.web.alumnidatamanagement.entity.Alumni
import sulic.web.alumnidatamanagement.entity.common.College
import sulic.web.alumnidatamanagement.entity.common.Gender
import java.io.Serializable

/**
 * DTO for {@link sulic.web.alumnidatamanagement.entity.Alumni}
 */
@Schema(description = "Alumni非敏信息Dto")
data class AlumniInfoDto(
    val id: Long? = null,
    val number: String? = null,
    val gender: Gender? = null,
    val birthday: String? = null,
    val college: College? = null,
    val klass: String? = null,
    val company: String? = null,
    val phone: String? = null,
    val email: String? = null,
    val major: String? = null
) : Serializable{
    companion object{
        fun fromEntity(a: Alumni): AlumniInfoDto {
            return AlumniInfoDto(
                a.id,
                a.number,
                a.gender,
                a.birthday,
                a.college,
                a.klass,
                a.company,
                a.phone,
                a.email,
                a.major)
        }
    }
}