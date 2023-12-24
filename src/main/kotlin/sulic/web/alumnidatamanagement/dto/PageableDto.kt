package sulic.web.alumnidatamanagement.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull

@Schema(description = "分页Dto")
data class PageableDto(
    @field:NotNull @field:Size(min = 0, max = 65535) val page: Int = 0,
    @field:NotNull @field:Size(min = 0, max = 65535) val size: Int = 0
)
