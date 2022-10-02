package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.model.response.ArticleResponse
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.ArticleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ArticleController(val articleService: ArticleService) {

    @GetMapping(
        value = ["api/articles"],
        produces = ["application/json"]
    )
    fun getAllArticles() : WebResponse<List<ArticleResponse>>{
        val articles = articleService.get()

        return WebResponse(
            code = 200,
            status = "success",
            data = articles
        )
    }

    @GetMapping(
        value = ["api/articles/{idArticle}"],
        produces = ["application/json"]
    )
    fun getArticleById(@PathVariable("idArticle") id: String) : WebResponse<ArticleResponse> {
        val article = articleService.getById(id)

        return WebResponse(
            code = 200,
            status = "success",
            data = article
        )
    }

    @GetMapping(
        value = ["api/articles/popular"],
        produces = ["application/json"]
    )
    fun getPopularArticle() : WebResponse<List<ArticleResponse>> {
        var articles = articleService.getByArticleCategoryId()

        return WebResponse(
            code = 200,
            status = "success",
            data = articles
        )
    }
}