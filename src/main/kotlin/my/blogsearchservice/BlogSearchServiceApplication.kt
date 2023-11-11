package my.blogsearchservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogSearchServiceApplication

fun main(args: Array<String>) {
    runApplication<BlogSearchServiceApplication>(*args)
}
