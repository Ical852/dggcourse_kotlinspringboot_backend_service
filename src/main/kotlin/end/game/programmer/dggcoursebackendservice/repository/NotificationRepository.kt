package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface NotificationRepository : JpaRepository<Notification, String> {
    @Query(value = "SELECT * FROM notification WHERE user_id = ?", nativeQuery = true)
    fun getNotificationByUserId(user_id: String) : List<Notification>
}