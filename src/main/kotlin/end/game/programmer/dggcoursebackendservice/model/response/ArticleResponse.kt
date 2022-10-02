package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.article.ArticleCategory
import end.game.programmer.dggcoursebackendservice.entity.article.Comment

data class ArticleResponse (
    val id: String,
    val author: String,
    val date: String,
    val description: String,
    val img: String,
    val title: String,
    val article_category: ArticleCategory?,
    val comments: List<CommentsResponse>
)