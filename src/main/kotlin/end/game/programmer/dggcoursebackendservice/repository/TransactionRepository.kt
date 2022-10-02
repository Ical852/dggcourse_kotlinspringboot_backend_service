package end.game.programmer.dggcoursebackendservice.repository

import end.game.programmer.dggcoursebackendservice.entity.transaction.Transaction
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TransactionRepository : JpaRepository<Transaction, String> {
    @Query(value = "SELECT * FROM transaction WHERE user_id = ?", nativeQuery = true)
    fun getTransactionByUserId(user_id: String) : List<Transaction>
}