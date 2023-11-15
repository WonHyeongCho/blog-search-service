package my.blogsearchservice.config

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import java.io.IOException
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class RFC1123DateTimeDeserializer : JsonDeserializer<ZonedDateTime>() {

    @Throws(IOException::class)
    override fun deserialize(parser: JsonParser, context: DeserializationContext): ZonedDateTime {
        val dateTimeString = parser.text
        return ZonedDateTime.parse(dateTimeString, DateTimeFormatter.RFC_1123_DATE_TIME)
    }
}
