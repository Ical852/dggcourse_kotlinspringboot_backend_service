package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank

data class UserCourseRequest (

    @field:NotBlank
    val course_id: String,

    @field:NotBlank
    val user_id: String
)