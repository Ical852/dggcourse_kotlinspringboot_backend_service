package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.model.response.ArticleResponse

interface ArticleService {
    fun get() : List<ArticleResponse>
    fun getById(id : String) : ArticleResponse
    fun getByArticleCategoryId() : List<ArticleResponse>
}