package my.blogsearchservice.controller

import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.service.BlogSearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/blog")
class BlogSearchController(
    private val blogSearchService: BlogSearchService
) {

    @GetMapping
    fun searchBlog(blogSearchRequestDto: BlogSearchRequestDto) {
        return blogSearchService.searchBlog(blogSearchRequestDto)
    }
}
