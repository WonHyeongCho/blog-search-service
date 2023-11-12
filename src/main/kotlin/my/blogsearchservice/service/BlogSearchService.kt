package my.blogsearchservice.service

import my.blogsearchservice.client.BlogSearchClient
import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.dto.BlogSearchResponseDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogSearchService(
    private val blogSearchClientMap: Map<String, BlogSearchClient>,
) {

    fun searchBlogFromKakao(blogSearchRequestDto: BlogSearchRequestDto): Mono<BlogSearchResponseDto> {
        return kakaoBlogSearchClient.search(blogSearchRequestDto).map {
            BlogSearchResponseDto(
                page = blogSearchRequestDto.page,
                size = blogSearchRequestDto.size,
                totalCount = it.meta.totalCount,
                sort = blogSearchRequestDto.sort,
                documents = it.documents
            )
        }
    }

    fun searchBlogFromNaver(blogSearchRequestDto: BlogSearchRequestDto): Mono<BlogSearchResponseDto> {
        return naverBlogSearchClient.search(blogSearchRequestDto).map {
            BlogSearchResponseDto(
                page = blogSearchRequestDto.page,
                size = blogSearchRequestDto.size,
                totalCount = it.total,
                sort = blogSearchRequestDto.sort,
                documents = it.items
            )
        }
    }
}
