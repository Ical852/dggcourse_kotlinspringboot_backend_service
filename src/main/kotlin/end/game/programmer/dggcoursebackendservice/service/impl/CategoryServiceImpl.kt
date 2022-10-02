package end.game.programmer.dggcoursebackendservice.service.impl

import end.game.programmer.dggcoursebackendservice.entity.course.Category
import end.game.programmer.dggcoursebackendservice.repository.CategoryRepository
import end.game.programmer.dggcoursebackendservice.service.CategoryService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CategoryServiceImpl(
    val categoryRepository: CategoryRepository
) : CategoryService {
    override fun get(): List<Category> {
        val categories = categoryRepository.findAll()

        return categories
    }

    override fun getById(id: String): Category {
        val category = categoryRepository.findByIdOrNull(id)

        return category!!
    }
}