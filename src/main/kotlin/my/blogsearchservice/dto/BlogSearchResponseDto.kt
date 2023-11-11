package my.blogsearchservice.dto

import my.blogsearchservice.domain.Blog

data class BlogSearchResponseDto(
    var page: Int,
    var size: Int,
    var totalCount: Int,
    var pageableCount: Int,
    var isEnd: Boolean,
    var documents: List<Blog>,
    var sort: String,
)
