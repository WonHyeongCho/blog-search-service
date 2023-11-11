package my.blogsearchservice.dto

data class BlogSearchRequestDto(
    val query: String,
    val sort: String = "accuracy",
    val page: Int = 1,
    val size: Int = 10,
)
