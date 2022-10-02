package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "rating")
data class Rating (

    @Id
    val id: String,

    @Column(name = "course_id")
    val courseId: String,

    @Column(name = "rating")
    var rating: Int
)