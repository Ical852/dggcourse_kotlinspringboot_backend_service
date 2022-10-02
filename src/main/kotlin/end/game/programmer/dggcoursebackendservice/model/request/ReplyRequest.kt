package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReplyRequest (

    @field:NotBlank
    val comment_id: String,

    @field:NotBlank
    val text: String,

    @field:NotNull
    val total_likes: Int,

    @field:NotBlank
    val user_id: String
)