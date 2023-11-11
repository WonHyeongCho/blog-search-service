package my.blogsearchservice.client.kakao

import my.blogsearchservice.config.BlogSourceConfiguration
import my.blogsearchservice.dto.BlogSearchRequestDto
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class KakaoBlogSearchClient(
    private val blogSourceConfiguration: BlogSourceConfiguration,
) {

    private val kakaoBlogSource = blogSourceConfiguration.getBlogSource("kakao")

    private val webClient: WebClient = WebClient.builder()
        .baseUrl(kakaoBlogSource.url)
        .defaultHeaders {
            it.set("Authorization", "KakaoAK ${kakaoBlogSource.apiKey}")
            it.set("Content-Type", "application/json")
            it.set("Accept", "application/json")
        }
        .build()

    fun search(query: String, sort: String, page: Int, size: Int): Mono<KakaoBlogSearchResponse> {
        return webClient.get()
            .uri {
                it.queryParam("query", query)
                    .queryParam("sort", sort)
                    .queryParam("page", page)
                    .queryParam("size", size)
                    .build()
            }
            .retrieve()
            .bodyToMono(KakaoBlogSearchResponse::class.java)
    }

    fun search(blogSearchRequestDto: BlogSearchRequestDto): Mono<KakaoBlogSearchResponse> {
        return search(
            query = blogSearchRequestDto.query,
            sort = blogSearchRequestDto.sort,
            page = blogSearchRequestDto.page,
            size = blogSearchRequestDto.size,
        )
    }
}
