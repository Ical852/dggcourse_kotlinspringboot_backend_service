package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.Notification
import end.game.programmer.dggcoursebackendservice.error.NotFoundException
import end.game.programmer.dggcoursebackendservice.model.request.NotificationRequest
import end.game.programmer.dggcoursebackendservice.repository.NotificationRepository
import end.game.programmer.dggcoursebackendservice.service.NotificationService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class NotificationServiceImpl(
    val notificationRepository: NotificationRepository,
    val validationUtil: ValidationUtil
) : NotificationService {
    override fun getByUserId(userId: String): List<Notification> {
        val notifications = notificationRepository.getNotificationByUserId(userId)

        if (notifications == null) {
            throw NotFoundException()
        } else {
            return notifications
        }
    }

    override fun getById(id: String): Notification {
        val notification = notificationRepository.findByIdOrNull(id)

        if (notification == null) {
            throw NotFoundException()
        } else {
            return notification
        }
    }

    override fun create(notificationRequest: NotificationRequest): Notification {
        validationUtil.validate(notificationRequest)

        val notification = Notification(
            id = UUID.randomUUID().toString(),
            date = notificationRequest.date,
            desc = notificationRequest.desc,
            title = notificationRequest.title,
            userId = notificationRequest.user_id
        )

        val result = notificationRepository.save(notification)

        return result
    }
}