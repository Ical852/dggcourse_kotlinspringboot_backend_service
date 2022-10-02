package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank

data class NotificationRequest (
    @field:NotBlank
    val date: String,

    @field:NotBlank
    val desc: String,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val user_id: String
)