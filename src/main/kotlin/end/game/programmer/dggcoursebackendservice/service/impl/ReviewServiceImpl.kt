package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.course.Review
import end.game.programmer.dggcoursebackendservice.model.request.ReviewRequest
import end.game.programmer.dggcoursebackendservice.model.response.ReviewResponse
import end.game.programmer.dggcoursebackendservice.repository.ReviewRepository
import end.game.programmer.dggcoursebackendservice.repository.UserRepository
import end.game.programmer.dggcoursebackendservice.service.ReviewService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class ReviewServiceImpl(
    val reviewRepository: ReviewRepository,
    val userRepository: UserRepository,
    val validationUtil: ValidationUtil
) : ReviewService {
    override fun giveReview(reviewRequest: ReviewRequest): ReviewResponse {
        validationUtil.validate(reviewRequest)

        val review = Review(
            id = UUID.randomUUID().toString(),
            courseId = reviewRequest.course_id,
            rate = reviewRequest.rate,
            text = reviewRequest.text,
            userId = reviewRequest.user_id
        )

        val result = reviewRepository.save(review)

        return convertReviewToReviewResponse(result)
    }

    private fun convertReviewToReviewResponse(review: Review) : ReviewResponse {
        val user = userRepository.findByIdOrNull(review.userId)

        return ReviewResponse(
            id = review.id,
            course_id = review.courseId,
            user_id = review.userId,
            text = review.text,
            rate = review.rate,
            user = user!!
        )
    }
}