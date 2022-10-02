package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.request.TransactionRequest
import end.game.programmer.dggcoursebackendservice.model.response.TransactionResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.TransactionService
import org.springframework.web.bind.annotation.*

@RestController
class TransactionController(val transactionService: TransactionService) {

    @PostMapping(
        value = ["api/transaction"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun createTransaction(@RequestBody body: TransactionRequest) : WebResponse<TransactionResponse> {
        val transaction = transactionService.create(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = transaction
        )
    }

    @GetMapping(
        value = ["api/transaction/user/{userId}"],
        produces = ["application/json"]
    )
    fun getAllTransaction(@PathVariable("userId") userId: String) : WebResponse<List<TransactionResponse>> {
        val transaction = transactionService.get(userId)

        return WebResponse(
            code = 200,
            status = "success",
            data = transaction
        )
    }

    @GetMapping(
        value = ["api/transaction/{id}"],
        produces = ["application/json"]
    )
    fun getransactionByiD(@PathVariable("id") id: String) : WebResponse<TransactionResponse> {
        val transaction = transactionService.getById(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = transaction
        )
    }
}