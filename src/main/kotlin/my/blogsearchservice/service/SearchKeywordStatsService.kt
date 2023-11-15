package my.blogsearchservice.service

import my.blogsearchservice.entity.SearchKeywordStat
import my.blogsearchservice.repository.SearchKeywordStatsRepository
import org.springframework.stereotype.Service

@Service
class SearchKeywordStatsService(
    private val searchKeywordStatsRepository: SearchKeywordStatsRepository
) {

    fun getTop10SearchKeywordStat(): List<SearchKeywordStat> {
        return searchKeywordStatsRepository.findTop10ByOrderByCountDesc()
    }
}
