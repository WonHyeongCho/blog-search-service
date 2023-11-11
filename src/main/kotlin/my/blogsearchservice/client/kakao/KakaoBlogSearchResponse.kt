package my.blogsearchservice.client.kakao

import my.blogsearchservice.domain.Blog
import java.time.LocalDateTime

class KakaoBlogSearchResponse {

    private val meta: Meta = Meta(0, 0, true)
    private val documents: List<Document> = emptyList()

    private class Meta(
        val totalCount: Int,
        val pageableCount: Int,
        val isEnd: Boolean
    )

    private class Document(
        val title: String,
        val contents: String,
        val url: String,
        val blogname: String,
        val thumbnail: String,
        val datetime: LocalDateTime
    )

    fun toBlogList(): List<Blog> {
        return this.documents.map { document ->
            Blog().apply {
                title = document.title
                contents = document.contents
                url = document.url
                blogName = document.blogname
                thumbnail = document.thumbnail
                datetime = document.datetime
            }
        }
    }
}
