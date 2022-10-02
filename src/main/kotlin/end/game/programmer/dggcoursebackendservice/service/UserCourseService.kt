package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.request.UserCourseRequest
import end.game.programmer.dggcoursebackendservice.model.response.UserCourseResponse

interface UserCourseService {
    fun create(userCourseRequest: UserCourseRequest) : UserCourseResponse
    fun get(userId: String) : List<UserCourseResponse>
    fun getById(id: String) : UserCourseResponse
}