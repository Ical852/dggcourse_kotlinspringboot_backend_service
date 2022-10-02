package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.response.CourseResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.CourseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CourseController(val courseService: CourseService) {

    @GetMapping(
        value = ["api/courses"],
        produces = ["application/json"]
    )
    fun getAllCourses() : WebResponse<List<CourseResponse>> {
        val courses = courseService.get()

        return WebResponse(
            code = 200,
            status = "success",
            data = courses
        )
    }

    @GetMapping(
        value = ["api/courses/{idCourse}"],
        produces = ["application/json"]
    )
    fun getCourseById(@PathVariable("idCourse") id: String) : WebResponse<CourseResponse> {
        val course = courseService.getById(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = course
        )
    }

    @GetMapping(
        value = ["api/courses/category/{idCourse}"],
        produces = ["application/json"]
    )
    fun getCourseByCategoryId(@PathVariable("idCourse") id: String) : WebResponse<List<CourseResponse>> {
        val courses = courseService.getByCategoryId(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = courses
        )
    }

    @GetMapping(
        value = ["api/courses/home"],
        produces = ["application/json"]
    )
    fun getLimitCourses() : WebResponse<List<CourseResponse>> {
        val courses = courseService.getSixAscCourse()

        return WebResponse(
            code = 200,
            status = "success",
            data = courses
        )
    }
}