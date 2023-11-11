package my.blogsearchservice.service

import my.blogsearchservice.client.kakao.KakaoBlogSearchClient
import my.blogsearchservice.dto.BlogSearchRequestDto
import org.springframework.stereotype.Service

@Service
class BlogSearchService(
    private val kakaoBlogSearchClient: KakaoBlogSearchClient,
) {

    fun searchBlog(blogSearchRequestDto: BlogSearchRequestDto) {
        val result = kakaoBlogSearchClient.search(blogSearchRequestDto).subscribe()
        println(result)
    }

}
