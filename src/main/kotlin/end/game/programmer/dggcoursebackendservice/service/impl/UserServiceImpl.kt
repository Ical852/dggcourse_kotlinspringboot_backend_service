package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.user.User
import end.game.programmer.dggcoursebackendservice.error.NotFoundException
import end.game.programmer.dggcoursebackendservice.model.request.LoginRequest
import end.game.programmer.dggcoursebackendservice.model.request.RegisterRequest
import end.game.programmer.dggcoursebackendservice.model.request.UpdateRequest
import end.game.programmer.dggcoursebackendservice.repository.UserRepository
import end.game.programmer.dggcoursebackendservice.service.UserService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val validationUtil: ValidationUtil
) : UserService {
    override fun fetchUser(id: String): User {
        val user = userRepository.findByIdOrNull(id)

        if (user == null) {
            throw NotFoundException()
        }

        return user
    }

    override fun register(registerRequest: RegisterRequest): User {
        validationUtil.validate(registerRequest)

        val user = User(
            id = UUID.randomUUID().toString(),
            full_name = registerRequest.full_name,
            email = registerRequest.email,
            password = registerRequest.password,
            role = "",
            interests = "",
            dggm = 0,
            img = "u1.jpeg",
            phone_number = registerRequest.phone_number
        )

        val result = userRepository.save(user)

        return result
    }

    override fun login(loginRequest: LoginRequest): User {
        validationUtil.validate(loginRequest)

        val user = userRepository.getUserByEmailAndPassword(
            email = loginRequest.email,
            password = loginRequest.password
        )

        return user
    }

    override fun updateUser(type: String, id: String, updateRequest: UpdateRequest): User {
        validationUtil.validate(updateRequest)

        val user = userRepository.findByIdOrNull(id)

        if (user == null) {
            throw NotFoundException()
        }

        if (type == "dggm") {
            user.apply {
                dggm = updateRequest.dggm
            }
        } else if(type == "profile") {
            user.apply {
                full_name = updateRequest.full_name
                phone_number = updateRequest.phone_number
            }
        } else if(type == "password") {
            user.apply {
                password = updateRequest.password
            }
        } else if(type == "role") {
            user.apply {
                role = updateRequest.role
            }
        } else if(type == "interest") {
            user.apply {
                interests = updateRequest.interests
            }
        }

        val result = userRepository.save(user)

        return result
    }
}