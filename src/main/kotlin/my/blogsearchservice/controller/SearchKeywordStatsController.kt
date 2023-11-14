package my.blogsearchservice.controller

import my.blogsearchservice.dto.KeywordRank
import my.blogsearchservice.dto.SearchKeywordRankResponseDto
import my.blogsearchservice.service.SearchKeywordStatsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/search-keyword/rank")
class SearchKeywordStatsController(
    private val searchKeywordStatsService: SearchKeywordStatsService
) {

    @GetMapping
    fun getTop10SearchKeyword(): ResponseEntity<SearchKeywordRankResponseDto> {
        return ResponseEntity.ok(
            SearchKeywordRankResponseDto(
                searchKeywordStatsService.getTop10SearchKeywordStat()
                    .mapIndexed { index, searchKeywordStat ->
                        KeywordRank(
                            index + 1, searchKeywordStat.keyword, searchKeywordStat.count
                        )
                    }
            )
        )
    }
}
