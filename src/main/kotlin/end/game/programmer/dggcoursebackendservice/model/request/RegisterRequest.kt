package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank

data class RegisterRequest (

    @field:NotBlank
    val email: String,

    @field:NotBlank
    val full_name: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val phone_number: String
)