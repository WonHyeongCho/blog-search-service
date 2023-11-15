package my.blogsearchservice.event

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import my.blogsearchservice.entity.SearchKeywordStat
import my.blogsearchservice.repository.SearchKeywordStatsRepository
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest
class SearchKeywordEventListenerTest(
    private val searchKeywordEventListener: SearchKeywordEventListener,
    private val searchKeywordStatsRepository: SearchKeywordStatsRepository
) : BehaviorSpec({

    given("동시에 100개의 키워드로 검색 요청이 들어왔을 때") {
        val keyword = "테스트"
        searchKeywordStatsRepository.save(SearchKeywordStat(keyword, 1L))

        val threadCount = 100
        val threadPool = 32
        val executorService: ExecutorService = Executors.newFixedThreadPool(threadPool)

        `when`("검색 키워드를 증가시킨다면") {
            (1..threadCount).forEach { _ ->
                executorService.submit {
                    searchKeywordEventListener.handleSearchKeywordEvent(SearchKeywordEvent(keyword))
                }
            }

            then("키워드가 총 101번 검색되어야 한다.") {
                withContext(Dispatchers.IO) {
                    Thread.sleep(5000) // 모든 쓰레드가 완료되길 기다리는 시간
                    searchKeywordStatsRepository.findById(keyword)
                }.get().count shouldBe 101
            }
        }
    }
})
