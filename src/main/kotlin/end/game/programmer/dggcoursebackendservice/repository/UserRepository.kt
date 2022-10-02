package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, String> {
    @Query("SELECT * FROM user WHERE email = ? AND password = ?", nativeQuery = true)
    fun getUserByEmailAndPassword(email: String, password: String) : User
}