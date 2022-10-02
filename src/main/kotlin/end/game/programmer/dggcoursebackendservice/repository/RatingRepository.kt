package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.course.Rating
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RatingRepository : JpaRepository<Rating, String> {
    @Query(value = "SELECT * FROM rating WHERE course_id = ? ", nativeQuery = true)
    fun getRatingByCourseId(course_id: String) : Rating
}