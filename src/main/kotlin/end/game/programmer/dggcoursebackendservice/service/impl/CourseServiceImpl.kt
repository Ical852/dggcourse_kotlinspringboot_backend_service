package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.course.Course
import end.game.programmer.dggcoursebackendservice.entity.course.Review
import end.game.programmer.dggcoursebackendservice.model.response.CourseResponse
import end.game.programmer.dggcoursebackendservice.model.response.ReviewResponse
import end.game.programmer.dggcoursebackendservice.repository.*
import end.game.programmer.dggcoursebackendservice.service.CourseService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CourseServiceImpl(
    val courseRepository: CourseRepository,
    val lessonRepository: LessonRepository,
    val toolRepository: ToolRepository,
    val resourceRepository: ResourceRepository,
    val reviewRepository: ReviewRepository,
    val mentorRepository: MentorRepository,
    val categoryRepository: CategoryRepository,
    val ratingRepository: RatingRepository,
    val userRepository: UserRepository
) : CourseService {

    override fun get(): List<CourseResponse> {
        val courses = courseRepository.findAll()

        return courses.map { convertCourseToCourseResponse(it) }
    }

    override fun getById(id: String): CourseResponse {
        val course = courseRepository.findByIdOrNull(id)

        return convertCourseToCourseResponse(course!!)
    }

    override fun getByCategoryId(category_id: String): List<CourseResponse> {
        val courses = courseRepository.getCourseByCategoryId(category_id)

        return courses.map { convertCourseToCourseResponse(it) }
    }

    override fun getSixAscCourse(): List<CourseResponse> {
        val courses = courseRepository.getCourseWithLimit()

        return courses.map { convertCourseToCourseResponse(it) }
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