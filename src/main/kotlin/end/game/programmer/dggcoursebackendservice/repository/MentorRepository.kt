package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.course.Mentor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MentorRepository : JpaRepository<Mentor, String> {
    @Query(value = "SELECT * FROM mentor WHERE course_id = ?", nativeQuery = true)
    fun getMentorByCourseId(course_id: String) : Mentor
}