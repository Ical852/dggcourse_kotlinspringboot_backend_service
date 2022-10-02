package end.game.programmer.dggcoursebackendservice.entity.user

import javax.persistence.*

@Entity
@Table(name = "user")
data class User (

    @Id
    val id: String,

    @Column(name = "full_name")
    var full_name: String,

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    var password: String,

    @Column(name = "phone_number")
    var phone_number: String,

    @Column(name = "dggm")
    var dggm: Int,

    @Column(name = "role")
    var role: String,

    @Column(name = "interests")
    var interests: String,

    @Column(name = "img")
    val img: String
)