package my.blogsearchservice.client.naver

import java.time.ZonedDateTime

class NaverBlogSearchResponse {

    var total: Long = 0
    var start: Int = 0
    var display: Int = 0
    var lastBuildDate: ZonedDateTime? = null
    var items: List<Item> = mutableListOf()
}

class Item {

    var title: String? = null
    var link: String? = null
    var description: String? = null
    var bloggername: String? = null
    var bloggerlink: String? = null
    var postdate: ZonedDateTime? = null
}
