package my.blogsearchservice.client.kakao

import com.fasterxml.jackson.annotation.JsonFormat
import my.blogsearchservice.domain.Blog
import java.time.LocalDateTime

class KakaoBlogSearchResponse {

    val meta: Meta = Meta()
    val documents: List<Document> = mutableListOf()

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

class Meta {

    val totalCount: Int = 0
    val pageableCount: Int = 0
    val isEnd: Boolean = false
}

class Document {

    val title: String? = null
    val contents: String? = null
    val url: String? = null
    val blogname: String? = null
    val thumbnail: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    val datetime: LocalDateTime? = null
}
