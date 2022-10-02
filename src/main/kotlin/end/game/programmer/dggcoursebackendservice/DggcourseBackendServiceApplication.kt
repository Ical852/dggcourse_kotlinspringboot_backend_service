package end.game.programmer.dggcoursebackendservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class DggcourseBackendServiceApplication : WebMvcConfigurer

fun main(args: Array<String>) {
	runApplication<DggcourseBackendServiceApplication>(*args)
}
