package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "lesson")
data class Lesson (

    @Id
    val id: String,

    @Column(name = "course_id")
    val courseId: String,

    @Column(name = "number")
    val number: String,

    @Column(name = "title")
    val title: String,

    @Column(name = "time")
    val time: String
)