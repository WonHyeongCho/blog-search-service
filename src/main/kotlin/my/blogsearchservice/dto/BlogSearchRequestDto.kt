package my.blogsearchservice.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class BlogSearchRequestDto(
    val query: String,
    val sort: String = "accuracy",
    @Min(1) @Max(50)
    val page: Int = 1,
    @Min(1) @Max(50)
    val size: Int = 10,
)
