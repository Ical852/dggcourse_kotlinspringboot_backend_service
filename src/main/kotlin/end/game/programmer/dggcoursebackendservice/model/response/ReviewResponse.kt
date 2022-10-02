package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.user.User

data class ReviewResponse (
    val id: String,
    val course_id: String,
    val user_id: String,
    val text: String,
    val rate: Int,
    val user: User
)