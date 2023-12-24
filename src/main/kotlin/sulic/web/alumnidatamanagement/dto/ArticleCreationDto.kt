package sulic.web.alumnidatamanagement.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import sulic.web.alumnidatamanagement.entity.Article
import java.io.Serializable

/**
 * DTO for {@link sulic.web.alumnidatamanagement.entity.Article}
 */
@Schema(description = "文章创建Dto")
data class ArticleCreationDto(
    val content: String? = null,
    @field:NotNull @field:Size(max = 255) @field:NotEmpty @field:NotBlank val title: String? = null,
    @field:NotNull @field:Size(max = 255) @field:NotEmpty @field:NotBlank val description: String? = null
) : Serializable{
    fun toEntity(): Article{
        return Article().apply {
            title = this@ArticleCreationDto.title
            description = this@ArticleCreationDto.description
            content = this@ArticleCreationDto.content
        }
    }
}