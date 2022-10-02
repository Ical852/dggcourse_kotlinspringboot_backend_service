package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.entity.Notification
import end.game.programmer.dggcoursebackendservice.model.request.NotificationRequest

interface NotificationService {
    fun getByUserId(userId: String) : List<Notification>
    fun getById(id : String) : Notification
    fun create(notificationRequest: NotificationRequest) : Notification
}