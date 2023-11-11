package my.blogsearchservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "blog-search-service")
class BlogSourceConfiguration {

    val sources: Map<String, BlogSource> = mutableMapOf()

    fun getBlogSource(sourceName: String): BlogSource {
        return sources[sourceName]
            ?: throw IllegalArgumentException("Invalid source name: $sourceName")
    }

    data class BlogSource(
        val apiKey: String = "",
        val url: String = ""
    )
}
