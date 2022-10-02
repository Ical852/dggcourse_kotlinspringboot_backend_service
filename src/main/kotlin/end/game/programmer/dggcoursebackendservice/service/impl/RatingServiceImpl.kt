package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.course.Rating
import end.game.programmer.dggcoursebackendservice.error.NotFoundException
import end.game.programmer.dggcoursebackendservice.model.request.RatingRequest
import end.game.programmer.dggcoursebackendservice.repository.RatingRepository
import end.game.programmer.dggcoursebackendservice.service.RatingService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.stereotype.Service

@Service
class RatingServiceImpl(
    val ratingRepository: RatingRepository,
    val validationUtil: ValidationUtil
) : RatingService {
    override fun postRating(ratingRequest: RatingRequest): Rating {
        validationUtil.validate(ratingRequest)

        val ratingData = ratingRepository.getRatingByCourseId(ratingRequest.course_id)

        if (ratingData == null) {
            throw NotFoundException()
        }

        ratingData.apply {
            rating = ratingData.rating + 1
        }

        val result = ratingRepository.save(ratingData)

        return result
    }
}