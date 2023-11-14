package my.blogsearchservice.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import my.blogsearchservice.entity.SearchKeywordStat
import my.blogsearchservice.repository.SearchKeywordStatsRepository
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest
class SearchKeywordStatsServiceTest(
    private val searchKeywordStatsService: SearchKeywordStatsService,
    private val searchKeywordStatsRepository: SearchKeywordStatsRepository,
) : BehaviorSpec({

    given("동시에 100개의 키워드로 검색 요청이 들어왔을 때") {
        val keyword = "테스트"
        withContext(Dispatchers.IO) {
            searchKeywordStatsRepository.save(SearchKeywordStat(keyword))
        }

        val threadCount = 100
        val threadPool = 32
        val executorService: ExecutorService = Executors.newFixedThreadPool(threadPool)
        val countDownLatch = CountDownLatch(threadCount)

        `when`("검색 키워드를 증가시킨다면") {
            (1..threadCount).forEach { _ ->
                executorService.submit {
                    searchKeywordStatsService.addSearchKeywordStat(keyword)
                    countDownLatch.countDown()
                }
            }

            withContext(Dispatchers.IO) {
                countDownLatch.await()
            }

            then("키워드가 총 100번 검색되어야 한다.") {
                withContext(Dispatchers.IO) {
                    searchKeywordStatsRepository.findById(keyword)
                }
                    .get().count shouldBe 100
            }
        }
    }
})
