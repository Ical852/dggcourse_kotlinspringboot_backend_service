package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "resource")
data class Resource (

    @Id
    val id: String,

    @Column(name = "course_id")
    val courseId: String,

    @Column(name = "number")
    val number: String,

    @Column(name = "title")
    val title: String,

    @Column(name = "source")
    val source: String
)