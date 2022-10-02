package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "tool")
data class Tool (

    @Id
    val id: String,

    @Column(name = "course_id")
    val courseId: String,

    @Column(name = "title")
    val title: String,

    @Column(name = "img")
    val img: String
)