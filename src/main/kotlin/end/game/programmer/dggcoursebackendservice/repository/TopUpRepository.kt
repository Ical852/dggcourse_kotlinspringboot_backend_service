package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.transaction.TopUp
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopUpRepository : JpaRepository<TopUp, String> {
    @Query(value = "SELECT * FROM topup WHERE user_id = ? ", nativeQuery = true)
    fun getTopUpByUserId(user_id: String) : List<TopUp>
}