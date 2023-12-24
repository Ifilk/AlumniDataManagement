package sulic.web.alumnidatamanagement.controller

import cn.dev33.satoken.annotation.SaCheckRole
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.*
import sulic.web.alumnidatamanagement.common.IFResult
import sulic.web.alumnidatamanagement.common.MESSAGE
import sulic.web.alumnidatamanagement.dto.AlumniInfoDto
import sulic.web.alumnidatamanagement.dto.ArticleCreationDto
import sulic.web.alumnidatamanagement.dto.PageableDto
import sulic.web.alumnidatamanagement.entity.Article
import sulic.web.alumnidatamanagement.repository.ArticleRepository

@RestController
@RequestMapping("api/v1/article")
@CrossOrigin(origins = ["*"])
class ArticleController(private val articleRepository: ArticleRepository) {
    @PostMapping
    @Operation(summary = "获取全部文章")
    fun getAllArticle(@Valid @RequestBody pageableDto: PageableDto): IFResult<Page<Article>> =
        IFResult.data(articleRepository.findAll(PageRequest.of(pageableDto.page, pageableDto.size)))

    @GetMapping("/{id}")
    @Operation(summary = "获取文章")
    fun getArticle(@PathVariable id: Long): IFResult<Article> =
        IFResult.data(articleRepository.findById(id).get())

    @PostMapping("publish")
    @Operation(summary = "发布文章")
    @SaCheckRole("Admin")
    fun publish(@Valid @RequestBody articleCreationDto: ArticleCreationDto) =
        IFResult.data(articleRepository.save(articleCreationDto.toEntity()))

    @DeleteMapping("/{id}")
    @Operation(summary = "删除文章")
    @SaCheckRole("Admin")
    fun delete(@PathVariable id: Long): MESSAGE{
        articleRepository.deleteById(id)
        return IFResult.ok()
    }
}