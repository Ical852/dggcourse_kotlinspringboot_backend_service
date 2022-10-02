package end.game.programmer.dggcoursebackendservice.entity.article

import javax.persistence.*

@Entity
@Table(name = "comment")
data class Comment (

    @Id
    val id: String,

    @Column(name = "article_id")
    val articleId: String,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "text")
    val text: String,

    @Column(name = "total_likes")
    var total_likes: Int
)