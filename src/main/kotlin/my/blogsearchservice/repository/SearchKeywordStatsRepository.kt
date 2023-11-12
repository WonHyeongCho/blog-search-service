package my.blogsearchservice.repository

import jakarta.persistence.LockModeType
import my.blogsearchservice.entity.SearchKeywordStat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface SearchKeywordStatsRepository : JpaRepository<SearchKeywordStat, String> {

    fun findTop10ByOrderByCountDesc(): List<SearchKeywordStat>

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from SearchKeywordStat s where s.keyword = :keyword")
    fun findByIdWithLock(keyword: String): SearchKeywordStat?
}
