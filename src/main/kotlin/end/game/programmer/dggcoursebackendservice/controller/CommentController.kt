package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.request.CommentRequest
import end.game.programmer.dggcoursebackendservice.model.response.CommentsResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.CommentService
import org.springframework.web.bind.annotation.*

@RestController
class CommentController(val commentService: CommentService) {

    @PostMapping(
        value = ["api/comments"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createNewComment(@RequestBody body: CommentRequest) : WebResponse<CommentsResponse> {
        val comment = commentService.postComment(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = comment
        )
    }

    @DeleteMapping(
        value = ["api/comments/{idComment}"],
        produces = ["application/json"]
    )
    fun deleteCommentById(@PathVariable("idComment") id: String) : WebResponse<String> {
        val comment = commentService.deleteComment(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = comment
        )
    }

    @GetMapping(
        value = ["api/comments/{idComment}"],
        produces = ["application/json"]
    )
    fun giveCommentLike(@PathVariable("idComment") id :String) : WebResponse<CommentsResponse> {
        val comment = commentService.likeComment(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = comment
        )
    }
}