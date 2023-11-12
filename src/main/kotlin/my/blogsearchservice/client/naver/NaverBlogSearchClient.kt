package my.blogsearchservice.client.naver

import my.blogsearchservice.client.BlogSearchClient
import my.blogsearchservice.config.BlogSourceConfiguration
import my.blogsearchservice.constant.CommonVariables
import my.blogsearchservice.constant.logger
import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.exception.BlogSearchServiceException
import my.blogsearchservice.exception.ErrorEnum
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component(CommonVariables.NAVER_BLOG_SOURCE_NAME)
class NaverBlogSearchClient(
    blogSourceConfiguration: BlogSourceConfiguration
) : BlogSearchClient {

    private val naverBlogSource =
        blogSourceConfiguration.getBlogSource(CommonVariables.NAVER_BLOG_SOURCE_NAME)

    private val webClient: WebClient = WebClient.builder()
        .baseUrl(naverBlogSource.url)
        .defaultHeaders {
            it.set("X-Naver-Client-Id", naverBlogSource.apiKey.split(":")[0])
            it.set("X-Naver-Client-Secret", naverBlogSource.apiKey.split(":")[1])
            it.set("Content-Type", "application/json")
            it.set("Accept", "application/json")
        }
        .build()

    fun search(blogSearchRequestDto: BlogSearchRequestDto): Mono<NaverBlogSearchResponse> {
        return search(
            query = blogSearchRequestDto.query,
            sort = if (blogSearchRequestDto.sort == "accuracy") "sim" else "date",
            page = blogSearchRequestDto.page,
            size = blogSearchRequestDto.size,
        )
    }

    override fun search(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Mono<NaverBlogSearchResponse> {
        return webClient.get()
            .uri {
                it.queryParam("query", query)
                    .queryParam("sort", sort)
                    .queryParam("start", page)
                    .queryParam("display", size)
                    .build()
            }
            .retrieve()
            .bodyToMono(NaverBlogSearchResponse::class.java)
    }

    override fun errorHandle(clientResponse: ClientResponse): Mono<Error> {
        return clientResponse.bodyToMono(Error::class.java)
            .flatMap {
                logger().error("Naver Blog Search API Error: ${it.message}")

                when (clientResponse.statusCode()) {
                    HttpStatus.BAD_REQUEST -> Mono.error(BlogSearchServiceException(ErrorEnum.API_BAD_REQUEST))
                    HttpStatus.FORBIDDEN -> Mono.error(BlogSearchServiceException(ErrorEnum.API_FORBIDDEN))
                    HttpStatus.NOT_FOUND -> Mono.error(BlogSearchServiceException(ErrorEnum.API_NOT_FOUND))
                    else -> Mono.error(BlogSearchServiceException(ErrorEnum.API_INTERNAL_SERVER_ERROR))
                }
            }
    }
}
