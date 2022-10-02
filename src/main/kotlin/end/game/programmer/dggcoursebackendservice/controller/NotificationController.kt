package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.entity.Notification
import end.game.programmer.dggcoursebackendservice.model.request.NotificationRequest
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.NotificationService
import org.springframework.web.bind.annotation.*

@RestController
class NotificationController(val notificationService: NotificationService) {

    @GetMapping(
        value = ["api/notifications/user/{idUser}"],
        produces = ["application/json"]
    )
    fun getNotification(@PathVariable("idUser") userId : String) : WebResponse<List<Notification>> {
        val notifications = notificationService.getByUserId(userId)

        return WebResponse(
            code = 200,
            status = "success",
            data = notifications
        )
    }

    @GetMapping(
        value = ["api/notifications/{idNotif}"],
        produces = ["application/json"]
    )
    fun getNotificationById(@PathVariable("idNotif") id: String) : WebResponse<Notification> {
        val notification = notificationService.getById(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = notification
        )
    }

    @PostMapping(
        value = ["api/notifications"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun createNotification(@RequestBody body: NotificationRequest) : WebResponse<Notification> {
        val notification = notificationService.create(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = notification
        )
    }
}