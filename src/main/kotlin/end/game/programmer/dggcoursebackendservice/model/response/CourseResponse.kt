package end.game.programmer.dggcoursebackendservice.model.response

import end.game.programmer.dggcoursebackendservice.entity.course.*

data class CourseResponse (
    val id: String,
    val img: String,
    val title: String,
    val desc: String,
    val price: Int,
    val category_id: String,
    val category: Category,
    val lessons: List<Lesson>,
    val tools: List<Tool>,
    val resources: List<Resource>,
    val reviews: List<ReviewResponse>,
    val mentor: Mentor,
    val rating: Rating
)