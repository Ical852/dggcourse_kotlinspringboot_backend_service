package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.course.Course
import end.game.programmer.dggcoursebackendservice.entity.course.Review
import end.game.programmer.dggcoursebackendservice.entity.user.UserCourse
import end.game.programmer.dggcoursebackendservice.error.NotFoundException
import end.game.programmer.dggcoursebackendservice.model.request.UserCourseRequest
import end.game.programmer.dggcoursebackendservice.model.response.CourseResponse
import end.game.programmer.dggcoursebackendservice.model.response.ReviewResponse
import end.game.programmer.dggcoursebackendservice.model.response.UserCourseResponse
import end.game.programmer.dggcoursebackendservice.repository.*
import end.game.programmer.dggcoursebackendservice.service.UserCourseService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserCourseServiceImpl(
    val userCourseRepository: UserCourseRepository,
    val courseRepository: CourseRepository,
    val lessonRepository: LessonRepository,
    val toolRepository: ToolRepository,
    val resourceRepository: ResourceRepository,
    val reviewRepository: ReviewRepository,
    val mentorRepository: MentorRepository,
    val categoryRepository: CategoryRepository,
    val ratingRepository: RatingRepository,
    val userRepository: UserRepository,
    val validationUtil: ValidationUtil
) : UserCourseService {
    override fun create(userCourseRequest: UserCourseRequest): UserCourseResponse {
        validationUtil.validate(userCourseRequest)

        val userCourse = UserCourse(
            id = UUID.randomUUID().toString(),
            course_id = userCourseRequest.course_id,
            userId = userCourseRequest.user_id
        )

        val result = userCourseRepository.save(userCourse)

        return convertUserCourseToUserCourseResponse(result)
    }

    override fun get(userId: String): List<UserCourseResponse> {
        val userCourses = userCourseRepository.getUserCourseByUserId(userId)

        return userCourses.map { convertUserCourseToUserCourseResponse(it) }
    }

    override fun getById(id: String): UserCourseResponse {
        val userCourse = userCourseRepository.findByIdOrNull(id)

        if (userCourse == null) {
            throw NotFoundException()
        }

        return convertUserCourseToUserCourseResponse(userCourse)
    }

    private fun convertUserCourseToUserCourseResponse(userCourse: UserCourse) : UserCourseResponse {
        val course = courseRepository.findByIdOrNull(userCourse.course_id)
        val user = userRepository.findByIdOrNull(userCourse.userId)

        return UserCourseResponse(
            id = userCourse.id,
            course_id = userCourse.course_id,
            user_id = userCourse.userId,
            user = user!!,
            courses = convertCourseToCourseResponse(course!!)
        )
    }

    private fun convertCourseToCourseResponse(course: Course) : CourseResponse {
        val category = categoryRepository.findByIdOrNull(course.categoryId)
        val lessons = lessonRepository.getLessonByCourseId(course.id)
        val tools = toolRepository.getToolByCourseId(course.id)
        val resources = resourceRepository.getResourceByCourseId(course.id)
        val reviews = reviewRepository.getReviewByCourseId(course.id)

        val reviewResponse = reviews.map { convertReviewToReviewResponse(it) }

        val mentor = mentorRepository.getMentorByCourseId(course.id)
        val rating = ratingRepository.getRatingByCourseId(course.id)

        return CourseResponse(
            id = course.id,
            img = course.img,
            title = course.title,
            desc = course.desc,
            price = course.price,
            category_id = course.categoryId,
            category = category!!,
            lessons = lessons,
            tools = tools,
            resources = resources,
            reviews = reviewResponse,
            mentor = mentor,
            rating = rating
        )
    }

    private fun convertReviewToReviewResponse(review: Review) : ReviewResponse {
        val user = userRepository.findByIdOrNull(review.userId)

        return ReviewResponse(
            id = review.id,
            course_id = review.courseId,
            user_id = review.userId,
            text = review.text,
            rate = review.rate,
            user = user!!
        )
    }
}