package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.user.User

data class TopUpResponse (
    val id: String,
    val order_id: String,
    val payment_url: String,
    val total: Int,
    val user_id: String,
    val user: User
)