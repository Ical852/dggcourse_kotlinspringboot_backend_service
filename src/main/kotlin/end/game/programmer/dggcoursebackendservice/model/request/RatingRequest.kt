package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class RatingRequest (

    @field:NotBlank
    val course_id: String,

    @field:NotNull
    val rating: Int
)