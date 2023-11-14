package my.blogsearchservice.service

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.verify
import my.blogsearchservice.dto.BlogSearchRequestDto
import my.blogsearchservice.exception.BlogSearchServiceException
import my.blogsearchservice.exception.ErrorEnum
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BlogSearchServiceTest(
    private val blogSearchService: BlogSearchService,
) : BehaviorSpec({

    given("블로그 검색 API를 호출했을 때") {
        val blogSearchRequestDto = BlogSearchRequestDto(
            query = "테스트",
            page = 1,
            size = 1000,
            sort = "WrongValue",
        )

        `when`("카카오 블로그 API 서비스가 장애가 발생하여 에러를 발생시킨다면") {
            val exception = shouldThrow<BlogSearchServiceException> {
                blogSearchService.searchBlogFromKakao(blogSearchRequestDto).block()
            }

            then("searchBlogFromKakao 메소드는 에러를 발생시킨다.") {
                exception.errorEnum shouldBe ErrorEnum.API_BAD_REQUEST
            }

            then("네이버 블로그 API 서비스를 호출한다.") {
                verify { blogSearchService.searchBlogFromNaver(blogSearchRequestDto) }
            }
        }
    }

})
