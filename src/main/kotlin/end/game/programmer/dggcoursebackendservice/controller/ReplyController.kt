package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.request.ReplyRequest
import end.game.programmer.dggcoursebackendservice.model.response.CommentsResponse
import end.game.programmer.dggcoursebackendservice.model.response.ReplyResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.ReplyService
import org.springframework.web.bind.annotation.*

@RestController
class ReplyController(val replyService: ReplyService) {

    @PostMapping(
        value = ["api/replies"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createNewReply(@RequestBody body: ReplyRequest) : WebResponse<ReplyResponse> {
        val reply = replyService.create(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = reply
        )
    }

    @DeleteMapping(
        value = ["api/replies/{idReply}"],
        produces = ["application/json"]
    )
    fun deleteReplyById(@PathVariable("idReply") id : String) : WebResponse<String> {
        val reply = replyService.delete(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = reply
        )
    }

    @GetMapping(
        value = ["api/replies/{idReply}"],
        produces = ["application/json"]
    )
    fun giveReplyLike(@PathVariable("idReply") id :String) : WebResponse<ReplyResponse> {
        val comment = replyService.likeReply(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = comment
        )
    }
}