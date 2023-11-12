package my.blogsearchservice.dto

data class BlogSearchResponseDto(
    var page: Int,
    var size: Int,
    var totalCount: Long,
    var documents: List<Any>,
    var sort: String,
)
