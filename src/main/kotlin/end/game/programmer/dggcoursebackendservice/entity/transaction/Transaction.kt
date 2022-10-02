package end.game.programmer.dggcoursebackendservice.entity.transaction

import javax.persistence.*

@Entity
@Table(name = "transaction")
data class Transaction (

    @Id
    val id: String,

    @Column(name = "course_id")
    val course_id: String,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "order_id")
    val order_id: String,

    @Column(name = "payment_url")
    var payment_url: String,

    @Column(name = "payment_type")
    val payment_type: String,

    @Column(name = "total")
    val total: Int
)