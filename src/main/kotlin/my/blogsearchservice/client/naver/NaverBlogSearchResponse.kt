package my.blogsearchservice.client.naver

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import my.blogsearchservice.config.RFC1123DateTimeDeserializer
import java.time.LocalDate
import java.time.ZonedDateTime

class NaverBlogSearchResponse {

    var total: Long = 0
    var start: Int = 0
    var display: Int = 0

    @JsonDeserialize(using = RFC1123DateTimeDeserializer::class)
    var lastBuildDate: ZonedDateTime? = null
    var items: List<Item> = mutableListOf()
}

class Item {

    var title: String? = null
    var link: String? = null
    var description: String? = null
    var bloggername: String? = null
    var bloggerlink: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMdd")
    var postdate: LocalDate? = null
}
