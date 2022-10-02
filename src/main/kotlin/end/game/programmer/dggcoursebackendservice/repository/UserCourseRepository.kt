package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.user.UserCourse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserCourseRepository : JpaRepository<UserCourse, String> {
    @Query(value = "SELECT * FROM user_course WHERE user_id = ?", nativeQuery = true)
    fun getUserCourseByUserId(user_id: String) : List<UserCourse>
}