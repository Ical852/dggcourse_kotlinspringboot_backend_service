package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.article.Comment
import end.game.programmer.dggcoursebackendservice.entity.article.Reply
import end.game.programmer.dggcoursebackendservice.error.NotFoundException
import end.game.programmer.dggcoursebackendservice.model.request.CommentRequest
import end.game.programmer.dggcoursebackendservice.model.response.CommentsResponse
import end.game.programmer.dggcoursebackendservice.model.response.ReplyResponse
import end.game.programmer.dggcoursebackendservice.repository.CommentRepository
import end.game.programmer.dggcoursebackendservice.repository.ReplyRepository
import end.game.programmer.dggcoursebackendservice.repository.UserRepository
import end.game.programmer.dggcoursebackendservice.service.CommentService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentServiceImpl(
    val commentRepository: CommentRepository,
    val userRepository: UserRepository,
    val replyRepository: ReplyRepository,
    val validationUtil: ValidationUtil
) : CommentService {

    override fun postComment(commentRequest: CommentRequest): CommentsResponse {
        validationUtil.validate(commentRequest)

        val comment = Comment(
            id = UUID.randomUUID().toString(),
            articleId = commentRequest.article_id,
            userId = commentRequest.user_id,
            text = commentRequest.text,
            total_likes = commentRequest.total_likes
        )

        val result = commentRepository.save(comment)

        return convertCommentToCommentResponse(result)
    }

    override fun deleteComment(id: String): String {
        val comment = commentRepository.findByIdOrNull(id)

        if (comment == null) {
            throw NotFoundException()
        } else {
            commentRepository.delete(comment)
        }

        return "success delete comment with id " + id
    }

    override fun likeComment(id: String): CommentsResponse {
        val comment = commentRepository.findByIdOrNull(id)

        if (comment == null) {
            throw NotFoundException()
        } else {
            comment.apply {
                total_likes = comment.total_likes + 1
            }

            commentRepository.save(comment)
        }

        return convertCommentToCommentResponse(comment)
    }

    private fun convertCommentToCommentResponse(comment: Comment) : CommentsResponse {
        val replies = replyRepository.getReplyByCommentId(comment_id = comment.id)
        val user = userRepository.findByIdOrNull(comment.userId)

        val repliesResponse = replies.map { convertReplyToReplyResponse(it) }

        return CommentsResponse(
            id = comment.id,
            article_id = comment.articleId,
            text = comment.text,
            total_likes = comment.total_likes,
            user = user,
            replies = repliesResponse,
            user_id = comment.userId
        )
    }

    private fun convertReplyToReplyResponse(reply: Reply) : ReplyResponse {
        val user = userRepository.findByIdOrNull(reply.userId)

        return ReplyResponse(
            id = reply.id,
            comment_id = reply.commentId,
            text = reply.text,
            total_likes = reply.total_likes,
            user = user,
            user_id = reply.userId
        )
    }
}