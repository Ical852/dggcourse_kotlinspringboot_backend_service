package end.game.programmer.dggcoursebackendservice.entity.article

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "article_category")
data class ArticleCategory (
    @Id
    val id: String,

    @Column(name = "name")
    val name: String,

)