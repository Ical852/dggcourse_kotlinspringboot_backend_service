package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.course.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ReviewRepository : JpaRepository<Review, String> {
    @Query(value = "SELECT * FROM review WHERE course_id = ? ", nativeQuery = true)
    fun getReviewByCourseId(course_id: String) : List<Review>
}