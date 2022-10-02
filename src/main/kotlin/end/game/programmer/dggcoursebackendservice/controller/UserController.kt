package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.entity.user.User
import end.game.programmer.dggcoursebackendservice.model.request.LoginRequest
import end.game.programmer.dggcoursebackendservice.model.request.RegisterRequest
import end.game.programmer.dggcoursebackendservice.model.request.UpdateRequest
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class UserController(val userService: UserService) {

    @PostMapping(
        value = ["api/user/login"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun login(@RequestBody body: LoginRequest) : WebResponse<User> {
        val user = userService.login(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = user
        )
    }

    @PostMapping(
        value = ["api/user/register"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun register(@RequestBody body: RegisterRequest) : WebResponse<User> {
        val user = userService.register(body)

        return WebResponse(
            code = 200,
            status = "success",
            data = user
        )
    }

    @GetMapping(
        value = ["api/user/{idUser}"],
        produces = ["application/json"]
    )
    fun getUser(@PathVariable("idUser") id : String) : WebResponse<User> {
        val user = userService.fetchUser(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = user
        )
    }

    @PutMapping(
        value = ["api/user/{idUser}"],
        consumes = ["application/json"],
        produces = ["application/json"]
    )
    fun update(
        @RequestParam(value = "type", defaultValue = "profile") type: String,
        @PathVariable("idUser") id: String,
        @RequestBody body: UpdateRequest
    ) : WebResponse<User> {
        val user = userService.updateUser(type, id, body)

        return WebResponse(
            code = 200,
            status = "success",
            data = user
        )
    }
}