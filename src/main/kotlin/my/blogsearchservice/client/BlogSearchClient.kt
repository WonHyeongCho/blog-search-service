package my.blogsearchservice.client

import org.springframework.web.reactive.function.client.ClientResponse
import reactor.core.publisher.Mono

interface BlogSearchClient {

    fun search(query: String, sort: String?, page: Int?, size: Int?): Mono<*>
    fun errorHandle(clientResponse: ClientResponse): Mono<Error>
}
