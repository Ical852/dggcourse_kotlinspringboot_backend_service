package end.game.programmer.dggcoursebackendservice.entity

import javax.persistence.*

@Entity
@Table(name = "notification")
data class Notification (

    @Id
    val id: String,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val desc: String,

    @Column(name = "date")
    val date: String

)