package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.article.Article
import end.game.programmer.dggcoursebackendservice.entity.article.Comment
import end.game.programmer.dggcoursebackendservice.entity.article.Reply
import end.game.programmer.dggcoursebackendservice.model.response.ArticleResponse
import end.game.programmer.dggcoursebackendservice.model.response.CommentsResponse
import end.game.programmer.dggcoursebackendservice.model.response.ReplyResponse
import end.game.programmer.dggcoursebackendservice.repository.*
import end.game.programmer.dggcoursebackendservice.service.ArticleService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArticleServiceImpl(
    val articleRepository: ArticleRepository,
    val articleCategoryRepository: ArticleCategoryRepository,
    val commentRepository: CommentRepository,
    val replyRepository: ReplyRepository,
    val userRepository: UserRepository
) : ArticleService {

    override fun get(): List<ArticleResponse> {
        val articles = articleRepository.findAll()

        return articles.map { convertArticleToArticlesResponse(it) }
    }

    override fun getById(id : String): ArticleResponse {
        val article = articleRepository.findByIdOrNull(id)

        return convertArticleToArticlesResponse(article!!)
    }

    override fun getByArticleCategoryId(): List<ArticleResponse> {
        val articles = articleRepository.getArticleByArticleCategoryId("5")

        return articles.map { convertArticleToArticlesResponse(it) }
    }

    private fun convertArticleToArticlesResponse(article: Article) : ArticleResponse {
        val comments = commentRepository.getCommentByArticleId(article_id = article.id)
        val articleCategory = articleCategoryRepository.findByIdOrNull(article.articeCategoryId)

        val commentsResponse = comments.map { convertCommentToCommentResponse(it) }

        return ArticleResponse(
            id = article.id,
            author = article.author,
            date = article.date,
            description = article.desc,
            img = article.img,
            title = article.title,
            article_category = articleCategory,
            comments = commentsResponse
        )
    }

    private fun convertCommentToCommentResponse(comment: Comment) : CommentsResponse {
        val replies = replyRepository.getReplyByCommentId(comment_id = comment.id)
        val user = userRepository.findByIdOrNull(comment.userId)

        val repliesResponse = replies.map { convertReplyToReplyResponse(it) }

        return CommentsResponse(
            id = comment.id,
            article_id = comment.articleId,
            text = comment.text,
            total_likes = comment.total_likes,
            user = user,
            replies = repliesResponse,
            user_id = comment.userId
        )
    }

    private fun convertReplyToReplyResponse(reply: Reply) : ReplyResponse {
        val user = userRepository.findByIdOrNull(reply.userId)

        return ReplyResponse(
            id = reply.id,
            comment_id = reply.commentId,
            text = reply.text,
            total_likes = reply.total_likes,
            user = user,
            user_id = reply.userId
        )
    }
}