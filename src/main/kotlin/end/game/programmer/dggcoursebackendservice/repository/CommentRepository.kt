package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.article.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CommentRepository : JpaRepository<Comment, String> {
    @Query(value = "SELECT * FROM comment WHERE article_id = ?", nativeQuery = true)
    fun getCommentByArticleId(article_id: String) : List<Comment>
}