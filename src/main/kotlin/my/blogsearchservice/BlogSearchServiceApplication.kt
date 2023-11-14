package my.blogsearchservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry

@EnableRetry
@SpringBootApplication
class BlogSearchServiceApplication

fun main(args: Array<String>) {
    runApplication<BlogSearchServiceApplication>(*args)
}
