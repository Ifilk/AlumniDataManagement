package sulic.web.alumnidatamanagement.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.io.Serializable

/**
 * DTO for {@link sulic.web.alumnidatamanagement.entity.Alumni}
 */
@Schema(description = "用户登录DTO")
data class UserLoginDto(
    @field:NotNull @field:Size(max = 15) @field:NotEmpty @field:NotBlank val number: String? = null,
    @field:NotNull @field:Size(max = 31) @field:NotEmpty @field:NotBlank val pwd: String? = null,
    @field:Schema(description = "图像验证码")@field:NotNull @field:Size(max = 6) @field:NotEmpty @field:NotBlank val imgCode: String? = null,
) : Serializable