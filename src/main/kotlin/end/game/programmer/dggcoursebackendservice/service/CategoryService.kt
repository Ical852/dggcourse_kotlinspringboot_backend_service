package end.game.programmer.dggcoursebackendservice.service

import end.game.programmer.dggcoursebackendservice.entity.course.Category

interface CategoryService {
    fun get() : List<Category>
    fun getById(id: String) : Category
}