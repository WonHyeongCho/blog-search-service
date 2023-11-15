package my.blogsearchservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.retry.annotation.EnableRetry
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@EnableRetry
@SpringBootApplication
class BlogSearchServiceApplication

fun main(args: Array<String>) {
    runApplication<BlogSearchServiceApplication>(*args)
}
