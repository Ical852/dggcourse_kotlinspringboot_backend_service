package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank

data class LoginRequest (

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val password: String
)