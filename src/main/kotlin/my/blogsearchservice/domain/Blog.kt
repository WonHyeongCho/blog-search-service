package my.blogsearchservice.domain

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.ZonedDateTime

class Blog {

    var title: String? = null
    var contents: String? = null
    var url: String? = null
    var blogName: String? = null
    var thumbnail: String? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    var datetime: ZonedDateTime? = null
}
