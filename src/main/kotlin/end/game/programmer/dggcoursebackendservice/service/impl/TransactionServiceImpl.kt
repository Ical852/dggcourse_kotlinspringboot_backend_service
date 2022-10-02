package end.game.programmer.dggcoursebackendservice.service.impl

import com.midtrans.Config
import com.midtrans.ConfigFactory
import com.midtrans.service.MidtransSnapApi
import end.game.programmer.dggcoursebackendservice.entity.course.Course
import end.game.programmer.dggcoursebackendservice.entity.course.Review
import end.game.programmer.dggcoursebackendservice.entity.transaction.Transaction
import end.game.programmer.dggcoursebackendservice.error.NotFoundException
import end.game.programmer.dggcoursebackendservice.model.request.TransactionRequest
import end.game.programmer.dggcoursebackendservice.model.response.CourseResponse
import end.game.programmer.dggcoursebackendservice.model.response.ReviewResponse
import end.game.programmer.dggcoursebackendservice.model.response.TransactionResponse
import end.game.programmer.dggcoursebackendservice.repository.*
import end.game.programmer.dggcoursebackendservice.service.TransactionService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionServiceImpl(
    val transactionRepository: TransactionRepository,
    val courseRepository: CourseRepository,
    val userRepository: UserRepository,
    val validationUtil: ValidationUtil,
    val categoryRepository: CategoryRepository,
    val lessonRepository: LessonRepository,
    val toolRepository: ToolRepository,
    val resourceRepository: ResourceRepository,
    val reviewRepository: ReviewRepository,
    val mentorRepository: MentorRepository,
    val ratingRepository: RatingRepository
) : TransactionService {
    override fun create(transactionRequest: TransactionRequest): TransactionResponse {
        validationUtil.validate(transactionRequest)

        val transaction = Transaction(
            id = UUID.randomUUID().toString(),
            course_id = transactionRequest.course_id,
            order_id = transactionRequest.order_id,
            payment_type = transactionRequest.payment_type,
            payment_url = "",
            total = transactionRequest.total,
            userId = transactionRequest.user_id
        )

        if (transactionRequest.payment_type == "dggm") {
            transaction.payment_url = "dggm payment"

            val result = transactionRepository.save(transaction)
            updateUserDggm(transactionRequest.total, transactionRequest.user_id)

            return convertTransactionToTransactionResponse(result)
        } else {
            val body = requestBody(transactionRequest.order_id, transactionRequest.total.toString())
            val url = getSnapUrl(body)

            transaction.payment_url = url
            val result = transactionRepository.save(transaction)

            return convertTransactionToTransactionResponse(result)
        }
    }

    override fun get(userId: String): List<TransactionResponse> {
        val transactions = transactionRepository.getTransactionByUserId(userId)

        return transactions.map { convertTransactionToTransactionResponse(it) }
    }

    override fun getById(id: String): TransactionResponse {
        val transaction = transactionRepository.findByIdOrNull(id)

        if (transaction == null) {
            throw NotFoundException()
        }

        return convertTransactionToTransactionResponse(transaction)
    }

    private fun updateUserDggm(total: Int, userId: String) {
        val user = userRepository.findByIdOrNull(userId)

        if (user == null) {
            throw NotFoundException()
        }

        user?.apply {
            dggm = user!!.dggm - total
        }

        userRepository.save(user)
    }

    private fun getSnapUrl(body: Map<String, Map<String, String>>) : String {
        val snapApi: MidtransSnapApi = ConfigFactory(Config("", "", false)).snapApi

        var redirectUrl = snapApi.createTransactionRedirectUrl(body)

        return redirectUrl
    }

    private fun requestBody(orderId: String, grossAmount: String) : Map<String, Map<String, String>> {
        var transactions: Map<String, String> = mapOf(
            "order_id" to orderId,
            "gross_amount" to grossAmount
        )

        var creditCard: Map<String, String> = mapOf(
            "secure" to "true"
        )

        var params : Map<String, Map<String, String>> = mapOf(
            "transaction_details" to transactions,
            "credit_card" to creditCard
        )

        return params
    }

    private fun convertTransactionToTransactionResponse(transaction: Transaction) : TransactionResponse {
        val course = courseRepository.findByIdOrNull(transaction.course_id)
        val user = userRepository.findByIdOrNull(transaction.userId)

        return TransactionResponse(
            id = transaction.id,
            course_id = transaction.course_id,
            order_id = transaction.order_id,
            payment_type = transaction.payment_type,
            payment_url = transaction.payment_url,
            total = transaction.total,
            user_id = transaction.userId,
            course = convertCourseToCourseResponse(course!!),
            user = user!!
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