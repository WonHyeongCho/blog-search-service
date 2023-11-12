package my.blogsearchservice.service

import my.blogsearchservice.client.redis.RedisClient
import my.blogsearchservice.domain.SearchKeywordStat

class SearchKeywordStatsService(
    private val redisClient: RedisClient
) {

    private val SEARCH_KEYWORD_STATS_KEY = "search_keyword_stats"

    fun addSearchKeywordStats(keyword: String) {
        if (redisClient.rankZSet(SEARCH_KEYWORD_STATS_KEY, keyword) == null) {
            redisClient.addZSet(SEARCH_KEYWORD_STATS_KEY, keyword, 1.0)
        } else {
            redisClient.incrementZSet(SEARCH_KEYWORD_STATS_KEY, keyword, 1.0)
        }
    }

    fun getTop10SearchKeywordStats(): List<SearchKeywordStat> {
        return redisClient.reverseRangeZSet(SEARCH_KEYWORD_STATS_KEY, 0, 9)
            ?.map {
                SearchKeywordStat().apply {
                    keyword = it.value as String
                    count = it.score
                }
            }
            ?: emptyList()
    }
}
