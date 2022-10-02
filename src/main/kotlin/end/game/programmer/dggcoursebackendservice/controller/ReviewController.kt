package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.request.ReviewRequest
import end.game.programmer.dggcoursebackendservice.model.response.ReviewResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.ReviewService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(val reviewService: ReviewService) {

    @PostMapping(
        value = ["api/review"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun giveReviewToCourse(@RequestBody body: ReviewRequest) : WebResponse<ReviewResponse> {
        val review = reviewService.giveReview(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = review
        )
    }
}