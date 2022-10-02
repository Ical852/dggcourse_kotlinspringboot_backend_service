package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.course.Category
import end.game.programmer.dggcoursebackendservice.entity.course.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CourseRepository : JpaRepository<Course, String> {
    @Query(value = "SELECT * FROM course WHERE category_id = ?", nativeQuery = true)
    fun getCourseByCategoryId(category_id: String) : List<Course>

    @Query(value = "SELECT * FROM course ORDER BY id ASC LIMIT 6", nativeQuery = true)
    fun getCourseWithLimit() : List<Course>
}