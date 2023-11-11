package my.blogsearchservice.controller

import my.blogsearchservice.domain.Blog
import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.service.BlogSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/blog")
class BlogSearchController(
    private val blogSearchService: BlogSearchService
) {

    @GetMapping
    fun searchBlog(blogSearchRequestDto: BlogSearchRequestDto): Mono<List<Blog>> {
        return blogSearchService.searchBlog(blogSearchRequestDto)
    }
}
