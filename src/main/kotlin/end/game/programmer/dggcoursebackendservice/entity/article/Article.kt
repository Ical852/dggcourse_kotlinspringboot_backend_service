package end.game.programmer.dggcoursebackendservice.entity.article

import javax.persistence.*

@Entity
@Table(name = "article")
data class Article (

    @Id
    val id: String,

    @Column(name = "title")
    val title: String,

    @Column(name = "description")
    val desc: String,

    @Column(name = "img")
    val img: String,

    @Column(name = "author")
    val author: String,

    @Column(name = "date")
    val date: String,

    @Column(name = "article_category_id")
    val articeCategoryId: String
)