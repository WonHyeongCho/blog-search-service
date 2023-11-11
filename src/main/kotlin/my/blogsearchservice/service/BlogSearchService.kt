package my.blogsearchservice.service

import my.blogsearchservice.client.kakao.KakaoBlogSearchClient
import my.blogsearchservice.domain.Blog
import my.blogsearchservice.dto.BlogSearchRequestDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogSearchService(
    private val kakaoBlogSearchClient: KakaoBlogSearchClient,
) {

    fun searchBlog(blogSearchRequestDto: BlogSearchRequestDto): Mono<List<Blog>> {
        return kakaoBlogSearchClient.search(blogSearchRequestDto).map {
            it.toBlogList()
        }

    }

}
