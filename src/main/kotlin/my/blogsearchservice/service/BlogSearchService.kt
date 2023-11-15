package my.blogsearchservice.service

import my.blogsearchservice.client.BlogSearchClient
import my.blogsearchservice.client.kakao.KakaoBlogSearchClient
import my.blogsearchservice.client.naver.NaverBlogSearchClient
import my.blogsearchservice.constant.CommonVariables
import my.blogsearchservice.dto.Blog
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
            getSearchSource(CommonVariables.KAKAO_BLOG_SOURCE_NAME) as KakaoBlogSearchClient

        return searchClient.search(blogSearchRequestDto).map {
            BlogSearchResponseDto(
                page = blogSearchRequestDto.page,
                size = blogSearchRequestDto.size,
                totalPage = it.meta.pageableCount,
                totalCount = it.meta.totalCount,
                documents = it.documents.map { document -> Blog(document) },
                sort = blogSearchRequestDto.sort,
                source = CommonVariables.KAKAO_BLOG_SOURCE_NAME,
            )
        }.onErrorResume { // 에러 발생 시 네이버 API 호출
            searchBlogFromNaver(blogSearchRequestDto)
        }
    }

    fun searchBlogFromNaver(blogSearchRequestDto: BlogSearchRequestDto): Mono<BlogSearchResponseDto> {
        val searchClient: NaverBlogSearchClient =
            getSearchSource(CommonVariables.NAVER_BLOG_SOURCE_NAME) as NaverBlogSearchClient

        return searchClient.search(blogSearchRequestDto).map {
            BlogSearchResponseDto(
                page = it.start,
                size = it.display,
                totalPage = (it.total / it.display).toInt(),
                totalCount = it.total,
                documents = it.items.map { item -> Blog(item) },
                sort = blogSearchRequestDto.sort,
                source = CommonVariables.NAVER_BLOG_SOURCE_NAME,
            )
        }
    }

    private fun getSearchSource(sourceName: String): BlogSearchClient {
        return blogSearchClientMap[sourceName]
            ?: throw IllegalArgumentException("Invalid source: $sourceName")

    }
}
