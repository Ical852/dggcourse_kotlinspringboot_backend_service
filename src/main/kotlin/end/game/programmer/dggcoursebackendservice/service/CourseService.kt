package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.response.CourseResponse

interface CourseService {
    fun get() : List<CourseResponse>
    fun getById(id: String) : CourseResponse
    fun getByCategoryId(category_id: String) : List<CourseResponse>
    fun getSixAscCourse() : List<CourseResponse>
}