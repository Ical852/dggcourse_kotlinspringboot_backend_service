package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.entity.course.Rating
import end.game.programmer.dggcoursebackendservice.model.request.RatingRequest
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.RatingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RatingController(val ratingService: RatingService) {

    @PostMapping(
        value = ["api/rating"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun rate(@RequestBody body: RatingRequest) : WebResponse<Rating> {
        val rate = ratingService.postRating(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = rate
        )
    }
}