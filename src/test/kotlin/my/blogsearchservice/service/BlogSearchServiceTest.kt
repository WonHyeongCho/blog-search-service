package my.blogsearchservice.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import my.blogsearchservice.dto.BlogSearchRequestDto
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BlogSearchServiceTest(
    private val blogSearchService: BlogSearchService,
) : BehaviorSpec({

    given("블로그 검색 API를 호출했을 때") {
        val blogSearchRequestDto = BlogSearchRequestDto(
            query = "테스트",
            page = 1,
            size = 70,
            sort = "accuracy",
        )

        `when`("카카오 블로그 API 서비스가 장애가 발생하여 에러를 발생시킨다면") {
            val blogSearchResponseDto =
                blogSearchService.searchBlogFromKakao(blogSearchRequestDto).block()

            then("네이버 블로그 API 서비스를 호출한다.") {
                blogSearchResponseDto?.source shouldBe "naver"
            }
        }
    }
})
