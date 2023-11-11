package my.blogsearchservice.service

import my.blogsearchservice.client.kakao.KakaoBlogSearchClient
import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.dto.BlogSearchResponseDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogSearchService(
    private val kakaoBlogSearchClient: KakaoBlogSearchClient,
) {

    fun searchBlog(blogSearchRequestDto: BlogSearchRequestDto): Mono<BlogSearchResponseDto> {
        return kakaoBlogSearchClient.search(blogSearchRequestDto).map {
            BlogSearchResponseDto(
                page = blogSearchRequestDto.page,
                size = blogSearchRequestDto.size,
                totalCount = it.meta.totalCount,
                pageableCount = it.meta.pageableCount,
                isEnd = it.meta.isEnd,
                sort = blogSearchRequestDto.sort,
                documents = it.toBlogList()
            )
        }
    }
}
