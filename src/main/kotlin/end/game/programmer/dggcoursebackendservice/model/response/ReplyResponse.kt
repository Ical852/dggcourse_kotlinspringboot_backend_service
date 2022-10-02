package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.user.User

data class ReplyResponse (
    val id: String,
    val comment_id: String,
    val text: String,
    val total_likes: Int,
    val user_id: String,
    val user: User?
)