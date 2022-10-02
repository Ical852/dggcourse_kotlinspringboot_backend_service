package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.request.CommentRequest
import end.game.programmer.dggcoursebackendservice.model.response.CommentsResponse

interface CommentService {
    fun postComment(commentRequest: CommentRequest) : CommentsResponse
    fun deleteComment(id: String) : String
    fun likeComment(id: String) : CommentsResponse
}