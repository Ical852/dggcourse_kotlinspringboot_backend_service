package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "course")
data class Course (

    @Id
    val id: String,

    @Column(name = "img")
    val img: String,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val desc: String,

    @Column(name = "price")
    val price: Int,

    @Column(name = "category_id")
    val categoryId: String
)