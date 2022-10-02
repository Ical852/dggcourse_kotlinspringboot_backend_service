package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.user.User

data class UserCourseResponse (
    val id: String,
    val course_id: String,
    val user_id: String,
    val user: User,
    val courses: CourseResponse
)