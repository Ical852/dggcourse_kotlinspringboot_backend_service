package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.transaction.TopUp
import end.game.programmer.dggcoursebackendservice.model.request.TopUpRequest
import end.game.programmer.dggcoursebackendservice.model.response.TopUpResponse
import end.game.programmer.dggcoursebackendservice.repository.TopUpRepository
import end.game.programmer.dggcoursebackendservice.repository.UserRepository
import end.game.programmer.dggcoursebackendservice.service.TopUpService
import end.game.programmer.dggcoursebackendservice.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import com.midtrans.Config
import com.midtrans.ConfigFactory
import com.midtrans.service.MidtransSnapApi
import end.game.programmer.dggcoursebackendservice.error.NotFoundException

@Service
class TopUpServiceImpl(
    val topUpRepository: TopUpRepository,
    val userRepository: UserRepository,
    val validationUtil: ValidationUtil
) : TopUpService {
    override fun topUp(topUpRequest: TopUpRequest): TopUpResponse {
        validationUtil.validate(topUpRequest)

        val topUp = TopUp(
            id = UUID.randomUUID().toString(),
            order_id = topUpRequest.order_id,
            payment_url = "",
            total = topUpRequest.total,
            userId = topUpRequest.user_id
        )

        val body = requestBody(topUpRequest.order_id, topUp.total.toString())
        val url = getSnapUrl(body)

        topUp.payment_url = url

        val result = topUpRepository.save(topUp)
        updateUserDggm(topUp.total, topUp.userId)

        return convertTopUpToTopUpResponse(result)
    }

    override fun getTopUp(userId: String): List<TopUpResponse> {
        val topUp = topUpRepository.getTopUpByUserId(userId)

        return topUp.map { convertTopUpToTopUpResponse(it) }
    }

    override fun getById(id: String): TopUpResponse {
        val topup = topUpRepository.findByIdOrNull(id)

        if (topup == null) {
            throw NotFoundException()
        }

        return convertTopUpToTopUpResponse(topup)
    }

    private fun updateUserDggm(total: Int, userId: String) {
        val user = userRepository.findByIdOrNull(userId)

        if (user == null) {
            throw NotFoundException()
        }

        user?.apply {
            dggm = user!!.dggm + total
        }

        userRepository.save(user)
    }

    private fun convertTopUpToTopUpResponse(topUp: TopUp) : TopUpResponse {
        val user = userRepository.findByIdOrNull(topUp.userId)

        return TopUpResponse(
            id = topUp.id,
            order_id = topUp.order_id,
            payment_url = topUp.payment_url,
            total = topUp.total,
            user_id = topUp.userId,
            user = user!!
        )
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
}