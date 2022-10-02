package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.request.TransactionRequest
import end.game.programmer.dggcoursebackendservice.model.response.TransactionResponse

interface TransactionService {
    fun create(transactionRequest: TransactionRequest) : TransactionResponse
    fun get(userId: String) : List<TransactionResponse>
    fun getById(id: String) : TransactionResponse
}