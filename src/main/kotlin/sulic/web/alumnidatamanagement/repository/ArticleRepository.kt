package sulic.web.alumnidatamanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository
import sulic.web.alumnidatamanagement.entity.Article

interface ArticleRepository : JpaRepository<Article, Long> {
}