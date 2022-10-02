package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.course.Tool
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ToolRepository : JpaRepository<Tool, String> {
    @Query(value = "SELECT * FROM tool WHERE course_id = ?", nativeQuery = true)
    fun getToolByCourseId(course_id: String) : List<Tool>
}