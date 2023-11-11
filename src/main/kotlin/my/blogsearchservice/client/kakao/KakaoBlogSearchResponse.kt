package my.blogsearchservice.client.kakao

import com.fasterxml.jackson.databind.annotation.JsonNaming
import my.blogsearchservice.domain.Blog
import java.time.ZonedDateTime

class KakaoBlogSearchResponse {

    var meta: Meta = Meta()
    var documents: List<Document> = mutableListOf()

    fun toBlogList(): List<Blog> {
        return documents.map { document ->
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

@JsonNaming(value = com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy::class)
class Meta {

    var totalCount: Int = 0
    var pageableCount: Int = 0
    var isEnd: Boolean = false
}

class Document {

    var title: String? = null
    var contents: String? = null
    var url: String? = null
    var blogname: String? = null
    var thumbnail: String? = null
    var datetime: ZonedDateTime? = null
}
