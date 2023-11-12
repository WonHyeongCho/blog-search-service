package my.blogsearchservice.service

import jakarta.transaction.Transactional
import my.blogsearchservice.entity.SearchKeywordStat
import my.blogsearchservice.repository.SearchKeywordStatsRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

@Service
class SearchKeywordStatsService(
    private val searchKeywordStatsRepository: SearchKeywordStatsRepository
) {

    fun getTop10SearchKeywordStat(): List<SearchKeywordStat> {
        return searchKeywordStatsRepository.findTop10ByOrderByCountDesc()
    }

    @Async
    @Transactional
    fun addSearchKeywordStat(keyword: String) {
        val searchKeywordStat = getSearchKeywordStatWithLock(keyword) ?: SearchKeywordStat(keyword)
        searchKeywordStat.incrementCount()
        saveSearchKeywordStat(searchKeywordStat)
    }

    private fun saveSearchKeywordStat(searchKeywordStat: SearchKeywordStat) {
        searchKeywordStatsRepository.save(searchKeywordStat)
    }

    private fun getSearchKeywordStatWithLock(keyword: String): SearchKeywordStat? {
        return searchKeywordStatsRepository.findByIdWithLock(keyword)
    }

}
