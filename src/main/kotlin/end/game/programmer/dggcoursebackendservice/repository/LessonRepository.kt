package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.course.Lesson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface LessonRepository : JpaRepository<Lesson, String> {
    @Query(value = "SELECT * FROM lesson WHERE course_id = ?", nativeQuery = true)
    fun getLessonByCourseId(course_id: String) : List<Lesson>
}