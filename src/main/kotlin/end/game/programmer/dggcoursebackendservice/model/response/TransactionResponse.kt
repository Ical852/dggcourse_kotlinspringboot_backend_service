package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.course.Course
import end.game.programmer.dggcoursebackendservice.entity.user.User

data class TransactionResponse (
    val id: String,
    val course_id: String,
    val order_id: String,
    val payment_type: String,
    val payment_url: String,
    val total: Int,
    val user_id: String,
    val course: CourseResponse,
    val user: User
)