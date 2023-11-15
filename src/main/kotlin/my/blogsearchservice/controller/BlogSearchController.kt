package my.blogsearchservice.controller

import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.dto.BlogSearchResponseDto
import my.blogsearchservice.event.SearchKeywordEvent
import my.blogsearchservice.service.BlogSearchService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/blog")
class BlogSearchController(
    private val blogSearchService: BlogSearchService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @GetMapping
    fun searchBlog(blogSearchRequestDto: BlogSearchRequestDto): Mono<BlogSearchResponseDto> {
        applicationEventPublisher.publishEvent(SearchKeywordEvent(blogSearchRequestDto.query))
        return blogSearchService.searchBlogFromKakao(blogSearchRequestDto)
    }
}
