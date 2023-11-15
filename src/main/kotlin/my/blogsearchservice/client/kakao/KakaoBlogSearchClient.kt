package my.blogsearchservice.client.kakao

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
import java.time.Duration

@Component(CommonVariables.KAKAO_BLOG_SOURCE_NAME)
class KakaoBlogSearchClient(
    blogSourceConfiguration: BlogSourceConfiguration,
) : BlogSearchClient {

    private val kakaoBlogSource =
        blogSourceConfiguration.getBlogSource(CommonVariables.KAKAO_BLOG_SOURCE_NAME)

    private val webClient: WebClient = WebClient.builder()
        .baseUrl(kakaoBlogSource.url)
        .defaultHeaders {
            it.set("Authorization", "KakaoAK ${kakaoBlogSource.apiKey}")
            it.set("Content-Type", "application/json")
            it.set("Accept", "application/json")
        }
        .build()

    fun search(blogSearchRequestDto: BlogSearchRequestDto): Mono<KakaoBlogSearchResponse> {
        return search(
            query = blogSearchRequestDto.query,
            sort = blogSearchRequestDto.sort,
            page = blogSearchRequestDto.page,
            size = blogSearchRequestDto.size,
        )
    }

    override fun search(
        query: String,
        sort: String?,
        page: Int?,
        size: Int?
    ): Mono<KakaoBlogSearchResponse> {
        return webClient.get()
            .uri {
                it.queryParam("query", query)
                    .queryParam("sort", sort)
                    .queryParam("page", page)
                    .queryParam("size", size)
                    .build()
            }
            .retrieve()
            .onStatus({ it.isError }, { errorHandle(it) })
            .bodyToMono(KakaoBlogSearchResponse::class.java)
            .timeout(Duration.ofSeconds(3))
    }

    override fun errorHandle(clientResponse: ClientResponse): Mono<Error> {

        return clientResponse.bodyToMono(Error::class.java)
            .flatMap {
                logger().error("Kakao Blog Search API Error: ${it.message}")

                when (clientResponse.statusCode()) {
                    HttpStatus.BAD_REQUEST -> Mono.error(BlogSearchServiceException(ErrorEnum.BAD_REQUEST))
                    HttpStatus.UNAUTHORIZED -> Mono.error(BlogSearchServiceException(ErrorEnum.API_UNAUTHORIZED))
                    HttpStatus.FORBIDDEN -> Mono.error(BlogSearchServiceException(ErrorEnum.API_FORBIDDEN))
                    HttpStatus.TOO_MANY_REQUESTS -> Mono.error(BlogSearchServiceException(ErrorEnum.API_TOO_MANY_REQUESTS))
                    else -> Mono.error(BlogSearchServiceException(ErrorEnum.API_INTERNAL_SERVER_ERROR))
                }
            }
    }
}
