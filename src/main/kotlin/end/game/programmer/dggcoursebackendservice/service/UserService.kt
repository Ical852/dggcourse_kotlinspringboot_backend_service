package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.entity.user.User
import end.game.programmer.dggcoursebackendservice.model.request.LoginRequest
import end.game.programmer.dggcoursebackendservice.model.request.RegisterRequest
import end.game.programmer.dggcoursebackendservice.model.request.UpdateRequest

interface UserService {
    fun fetchUser(id: String) : User
    fun register(registerRequest: RegisterRequest) : User
    fun login(loginRequest: LoginRequest) : User
    fun updateUser(type: String, id: String, updateRequest: UpdateRequest) : User
}