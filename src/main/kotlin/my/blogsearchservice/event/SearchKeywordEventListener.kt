package my.blogsearchservice.event

import jakarta.transaction.Transactional
import my.blogsearchservice.entity.SearchKeywordStat
import my.blogsearchservice.repository.SearchKeywordStatsRepository
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
class SearchKeywordEventListener(
    private val searchKeywordStatsRepository: SearchKeywordStatsRepository
) {

    @EventListener
    @Async
    @Transactional
    fun handleSearchKeywordEvent(searchKeywordEvent: SearchKeywordEvent) {
        val searchKeywordStat: SearchKeywordStat? =
            getSearchKeywordStatWithLock(searchKeywordEvent.keyword)

        if (searchKeywordStat == null) {
            saveSearchKeywordStat(SearchKeywordStat(searchKeywordEvent.keyword))
        } else {
            searchKeywordStat.incrementCount()
            saveSearchKeywordStat(searchKeywordStat)
        }
    }

    private fun saveSearchKeywordStat(searchKeywordStat: SearchKeywordStat) {
        searchKeywordStatsRepository.save(searchKeywordStat)
    }

    private fun getSearchKeywordStatWithLock(keyword: String): SearchKeywordStat? {
        return searchKeywordStatsRepository.findByIdWithLock(keyword)
    }
}
