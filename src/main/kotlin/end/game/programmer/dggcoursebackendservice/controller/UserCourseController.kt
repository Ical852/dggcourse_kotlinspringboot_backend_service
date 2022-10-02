package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.request.UserCourseRequest
import end.game.programmer.dggcoursebackendservice.model.response.UserCourseResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.UserCourseService
import org.springframework.web.bind.annotation.*

@RestController
class UserCourseController(val userCourseService: UserCourseService) {

    @PostMapping(
        value = ["api/user-course"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun createUserCourse(@RequestBody body: UserCourseRequest) : WebResponse<UserCourseResponse> {
        val userCourse = userCourseService.create(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = userCourse
        )
    }

    @GetMapping(
        value = ["api/user-course/user/{userId}"],
        produces = ["application/json"]
    )
    fun getUserCourseByUserId(@PathVariable("userId") userId: String) : WebResponse<List<UserCourseResponse>> {
        val userCourses = userCourseService.get(userId)

        return WebResponse(
            code = 200,
            status = "success",
            data = userCourses
        )
    }

    @GetMapping(
        value = ["api/user-course/{id}"],
        produces = ["application/json"]
    )
    fun getUserCourseById(@PathVariable("id") id: String) : WebResponse<UserCourseResponse> {
        val userCourse = userCourseService.getById(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = userCourse
        )
    }
}