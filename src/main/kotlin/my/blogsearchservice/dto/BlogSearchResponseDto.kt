package my.blogsearchservice.dto

import my.blogsearchservice.domain.Blog

data class BlogSearchResponseDto(
    val documents: List<Blog>,
)
