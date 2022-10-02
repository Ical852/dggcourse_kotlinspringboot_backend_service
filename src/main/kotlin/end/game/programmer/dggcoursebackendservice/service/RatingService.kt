package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.entity.course.Rating
import end.game.programmer.dggcoursebackendservice.model.request.RatingRequest

interface RatingService {
    fun postRating(ratingRequest: RatingRequest) : Rating
}