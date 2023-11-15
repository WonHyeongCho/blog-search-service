package my.blogsearchservice.dto

import my.blogsearchservice.client.kakao.Document
import my.blogsearchservice.client.naver.Item
import java.time.LocalDate

data class BlogSearchResponseDto(
    var page: Int,
    var size: Int,
    var totalPage: Int,
    var totalCount: Long,
    var documents: List<Blog>,
    var sort: String,
)

data class Blog(
    var title: String? = null,
    var contents: String? = null,
    var url: String? = null,
    var blogname: String? = null,
    var thumbnail: String? = null,
    var datetime: LocalDate? = null,
) {

    // Kakao Blog 검색 결과로부터 생성자
    constructor(document: Document) : this(
        document.title,
        document.contents,
        document.url,
        document.blogname,
        document.thumbnail,
        document.datetime?.toLocalDate()
    )

    // Naver Blog 검색 결과로부터 생성자
    constructor(item: Item) : this(
        item.title,
        item.description,
        item.link,
        item.bloggername,
        null,
        item.postdate
    )
}
