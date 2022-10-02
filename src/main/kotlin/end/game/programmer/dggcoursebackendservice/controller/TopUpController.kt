package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.request.TopUpRequest
import end.game.programmer.dggcoursebackendservice.model.response.TopUpResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.TopUpService
import org.springframework.web.bind.annotation.*

@RestController
class TopUpController(val topUpService: TopUpService) {

    @PostMapping(
        value = ["api/topup"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun topUp(@RequestBody body: TopUpRequest) : WebResponse<TopUpResponse> {
        val topUp = topUpService.topUp(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = topUp
        )
    }

    @GetMapping(
        value = ["api/topup/user/{userId}"],
        produces = ["application/json"]
    )
    fun getAllTopUp(@PathVariable("userId") userId : String) : WebResponse<List<TopUpResponse>> {
        val topUp = topUpService.getTopUp(userId)

        return WebResponse(
            code = 200,
            status = "success",
            data = topUp
        )
    }

    @GetMapping(
        value = ["api/topup/{id}"],
        produces = ["application/json"]
    )
    fun getTopUpById(@PathVariable("id") id : String) : WebResponse<TopUpResponse> {
        val topUp = topUpService.getById(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = topUp
        )
    }
}