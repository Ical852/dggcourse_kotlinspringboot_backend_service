package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "review")
data class Review (

    @Id
    val id: String,

    @Column(name = "course_id")
    val courseId: String,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "text")
    val text: String,

    @Column(name = "rate")
    val rate: Int
)