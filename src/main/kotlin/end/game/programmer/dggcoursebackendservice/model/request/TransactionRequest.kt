package end.game.programmer.dggcoursebackendservice.model.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class TransactionRequest (

    @field:NotBlank
    val course_id: String,

    @field:NotBlank
    val order_id: String,

    @field:NotBlank
    val payment_type: String,

    @field:NotNull
    val total: Int,

    @field:NotBlank
    val user_id: String
)