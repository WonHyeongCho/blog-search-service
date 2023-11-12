package my.blogsearchservice.dto

data class SearchKeywordRankResponseDto(
    var rank: List<KeywordRank>
)

class KeywordRank(
    var rank: Int,
    var keyword: String?,
    var count: Long
)
