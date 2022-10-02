package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CommentRequest (

    @field:NotBlank
    val article_id: String,

    @field:NotBlank
    val text: String,

    @field:NotNull
    @field:Min(0)
    val total_likes: Int,

    @field:NotBlank
    val user_id: String
)