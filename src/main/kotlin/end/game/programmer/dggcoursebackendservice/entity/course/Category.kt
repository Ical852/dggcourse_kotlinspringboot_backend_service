package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "category")
data class Category (

    @Id
    val id: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "img")
    val img: String,

    @Column(name = "total_course")
    val total_course: Int
)