package my.blogsearchservice.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

data class BlogSearchRequestDto(
    val query: String,
    val sort: String = "accuracy",
    @field:Min(value = 1) @field:Max(value = 50)
    val page: Int = 1,
    @field:Min(value = 1) @field:Max(value = 50)
    val size: Int = 10,
)
