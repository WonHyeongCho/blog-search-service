package my.blogsearchservice.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("blog-search-service.blog-source")
class BlogSourceConfiguration {

    private val sourceMap: Map<String, BlogSource> = mutableMapOf()

    fun getBlogSource(sourceName: String): BlogSource {
        return sourceMap[sourceName]
            ?: throw IllegalArgumentException("Invalid source name: $sourceName")
    }

    class BlogSource {

        lateinit var apiKey: String
        lateinit var url: String
    }
}
