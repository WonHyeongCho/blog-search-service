package my.blogsearchservice.service

import my.blogsearchservice.client.BlogSearchClient
import my.blogsearchservice.client.kakao.KakaoBlogSearchClient
import my.blogsearchservice.client.naver.NaverBlogSearchClient
import my.blogsearchservice.constant.CommonVariables
import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.dto.BlogSearchResponseDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogSearchService(
    private val blogSearchClientMap: Map<String, BlogSearchClient>,
) {

    fun searchBlogFromKakao(blogSearchRequestDto: BlogSearchRequestDto): Mono<BlogSearchResponseDto> {
        val searchClient: KakaoBlogSearchClient =
            (blogSearchClientMap[CommonVariables.KAKAO_BLOG_SOURCE_NAME]
                ?: throw IllegalArgumentException("Invalid source: ${CommonVariables.KAKAO_BLOG_SOURCE_NAME}"))
                as KakaoBlogSearchClient

        return searchClient.search(blogSearchRequestDto).map {
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
        val searchClient: NaverBlogSearchClient =
            (blogSearchClientMap[CommonVariables.NAVER_BLOG_SOURCE_NAME]
                ?: throw IllegalArgumentException("Invalid source: ${CommonVariables.NAVER_BLOG_SOURCE_NAME}"))
                as NaverBlogSearchClient

        return searchClient.search(blogSearchRequestDto).map {
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
