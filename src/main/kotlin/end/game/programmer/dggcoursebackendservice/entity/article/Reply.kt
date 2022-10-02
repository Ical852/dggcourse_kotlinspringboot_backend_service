package end.game.programmer.dggcoursebackendservice.entity.article

import javax.persistence.*

@Entity
@Table(name = "reply")
data class Reply (

    @Id
    val id: String,

    @Column(name = "comment_id")
    val commentId: String,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "text")
    val text: String,

    @Column(name = "total_likes")
    var total_likes: Int
)