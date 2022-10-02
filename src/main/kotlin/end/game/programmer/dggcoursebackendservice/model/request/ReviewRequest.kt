package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ReviewRequest (

    @field:NotBlank
    val course_id: String,

    @field:NotNull
    val rate: Int,

    @field:NotBlank
    val text: String,

    @field:NotBlank
    val user_id: String
)