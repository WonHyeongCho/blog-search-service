package my.blogsearchservice.dto

data class BlogSearchRequestDto(
    val query: String,
    val sort: String,
    val page: Int,
    val size: Int
)
