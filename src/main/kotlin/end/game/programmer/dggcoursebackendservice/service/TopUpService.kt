package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.request.TopUpRequest
import end.game.programmer.dggcoursebackendservice.model.response.TopUpResponse

interface TopUpService {
    fun topUp(topUpRequest: TopUpRequest) : TopUpResponse
    fun getTopUp(userId: String) : List<TopUpResponse>
    fun getById(id: String) : TopUpResponse
}