package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.article.Reply
import end.game.programmer.dggcoursebackendservice.error.NotFoundException
import end.game.programmer.dggcoursebackendservice.model.request.ReplyRequest
import end.game.programmer.dggcoursebackendservice.model.response.ReplyResponse
import end.game.programmer.dggcoursebackendservice.repository.ReplyRepository
import end.game.programmer.dggcoursebackendservice.repository.UserRepository
import end.game.programmer.dggcoursebackendservice.service.ReplyService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReplyServiceImpl(
    val replyRepository: ReplyRepository,
    val userRepository: UserRepository,
    val validationUtil: ValidationUtil
) : ReplyService {
    override fun create(replyRequest: ReplyRequest): ReplyResponse {
        validationUtil.validate(replyRequest)

        val reply = Reply(
            id = UUID.randomUUID().toString(),
            commentId = replyRequest.comment_id,
            text = replyRequest.text,
            total_likes = replyRequest.total_likes,
            userId = replyRequest.user_id
        )

        val result = replyRepository.save(reply)

        return convertReplyToReplyResponse(result)
    }

    override fun delete(id: String): String {
        val reply = replyRepository.findByIdOrNull(id)

        if (reply == null) {
            throw NotFoundException()
        } else {
            replyRepository.delete(reply)
        }

        return "success delete comment with id " + id
    }

    override fun likeReply(id: String): ReplyResponse {
        val reply = replyRepository.findByIdOrNull(id)

        if (reply == null) {
            throw NotFoundException()
        } else {
            reply.apply {
                total_likes = reply.total_likes + 1
            }

            replyRepository.save(reply)
        }

        return convertReplyToReplyResponse(reply)
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