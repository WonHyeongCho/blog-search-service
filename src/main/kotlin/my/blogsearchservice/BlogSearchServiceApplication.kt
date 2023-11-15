package my.blogsearchservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class BlogSearchServiceApplication

fun main(args: Array<String>) {
    runApplication<BlogSearchServiceApplication>(*args)
}
