package my.blogsearchservice.constant

import org.slf4j.Logger
import org.slf4j.LoggerFactory

inline fun <reified T> T.logger(): Logger {
    if (T::class.isCompanion) {
        return LoggerFactory.getLogger(T::class.java.enclosingClass)
    }
    return LoggerFactory.getLogger(T::class.java)
}

class CommonVariables {

    companion object {

        const val KAKAO_BLOG_SOURCE_NAME = "kakao"
        const val NAVER_BLOG_SOURCE_NAME = "naver"
    }
}
