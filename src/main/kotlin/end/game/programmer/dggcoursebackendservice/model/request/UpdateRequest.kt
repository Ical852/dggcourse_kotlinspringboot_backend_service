package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class UpdateRequest (
    @field:NotBlank
    val full_name: String,

    @field:NotBlank
    val phone_number: String,

    @field:NotBlank
    val password: String,

    @field:NotNull
    val dggm: Int,

    val role: String,

    val interests: String
)