package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.article.ArticleCategory
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleCategoryRepository: JpaRepository<ArticleCategory, String> {
}