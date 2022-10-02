package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.request.ReviewRequest
import end.game.programmer.dggcoursebackendservice.model.response.ReviewResponse

interface ReviewService {
    fun giveReview(reviewRequest: ReviewRequest) : ReviewResponse
}