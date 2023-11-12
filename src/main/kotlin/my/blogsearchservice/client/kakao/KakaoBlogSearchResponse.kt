package my.blogsearchservice.client.kakao

import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.ZonedDateTime

class KakaoBlogSearchResponse {

    var meta: Meta = Meta()
    var documents: List<Document> = mutableListOf()
}

@JsonNaming(value = com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy::class)
class Meta {

    var totalCount: Long = 0
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
