package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.article.Article
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ArticleRepository : JpaRepository<Article, String> {
    @Query(value = "SELECT * FROM article WHERE article_category_id = ?", nativeQuery = true)
    fun getArticleByArticleCategoryId(article_category_id: String) : List<Article>
}