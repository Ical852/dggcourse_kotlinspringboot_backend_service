package end.game.programmer.dggcoursebackendservice.controller

import end.game.programmer.dggcoursebackendservice.entity.course.Category
import end.game.programmer.dggcoursebackendservice.model.response.WebResponse
import end.game.programmer.dggcoursebackendservice.service.CategoryService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController(val categoryService: CategoryService) {

    @GetMapping(
        value = ["api/categories"],
        produces = ["application/json"]
    )
    fun getAllCategories() : WebResponse<List<Category>> {
        val categories = categoryService.get()

        return WebResponse(
            code = 200,
            status = "success",
            data = categories
        )
    }

    @GetMapping(
        value = ["api/categories/{idCategory}"],
        produces = ["application/json"]
    )
    fun getCategoryById(@PathVariable("idCategory") id: String ) : WebResponse<Category> {
        val category = categoryService.getById(id)

        return WebResponse(
            code = 200,
            status = "success",
            category
        )
    }
}