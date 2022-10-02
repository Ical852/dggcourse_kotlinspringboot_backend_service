package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.article.Reply
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ReplyRepository : JpaRepository<Reply, String> {
    @Query(name = "SELECT * FROM reply WHERE comment_id = ?", nativeQuery = true)
    fun getReplyByCommentId(comment_id: String) : List<Reply>
}