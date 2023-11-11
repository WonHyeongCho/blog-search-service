package my.blogsearchservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class BasicConfig {

    @Bean("kakaoWebClient")
    fun kakaoWebClient(): WebClient {
        return WebClient.builder()
            .build()
    }

}
