package end.game.programmer.dggcoursebackendservice.entity.user

import javax.persistence.*

@Entity
@Table(name = "user_course")
data class UserCourse (

    @Id
    val id: String,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "course_id")
    val course_id: String
)