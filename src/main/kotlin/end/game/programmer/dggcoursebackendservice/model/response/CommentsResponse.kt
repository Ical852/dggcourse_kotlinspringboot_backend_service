package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.user.User

data class CommentsResponse (
    val id: String,
    val article_id: String,
    val text: String,
    val total_likes: Int,
    val user_id: String,
    val user: User?,
    val replies: List<ReplyResponse>
)