package end.game.programmer.dggcoursebackendservice.entity.course

import javax.persistence.*

@Entity
@Table(name = "mentor")
data class Mentor (

    @Id
    val id: String,

    @Column(name = "course_id")
    val courseId: String,

    @Column(name = "img")
    val img: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "job")
    val job: String,

    @Column(name = "jobdesk")
    val jobdesk: String,

    @Column(name = "about_job")
    val about_job: String,

    @Column(name = "job_resp")
    val job_res: String,

    @Column(name = "about_company")
    val about_company: String,

    @Column(name = "job_history")
    val job_history: String
)