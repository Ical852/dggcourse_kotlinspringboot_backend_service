package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.request.ReplyRequest
import end.game.programmer.dggcoursebackendservice.model.response.ReplyResponse

interface ReplyService {
    fun create(replyRequest: ReplyRequest) : ReplyResponse
    fun delete(id : String) : String
    fun likeReply(id: String) : ReplyResponse
}