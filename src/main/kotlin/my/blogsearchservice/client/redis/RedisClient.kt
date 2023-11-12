package my.blogsearchservice.client.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations
import org.springframework.stereotype.Component

@Component
class RedisClient(
    private val redisTemplate: RedisTemplate<String, Any>
) {

    fun addZSet(key: String, value: Any, score: Double) {
        redisTemplate.opsForZSet().add(key, value, score)
    }

    fun reverseRangeZSet(
        key: String,
        start: Long,
        end: Long
    ): MutableSet<ZSetOperations.TypedTuple<Any>>? {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end)
    }

    fun incrementZSet(
        key: String,
        value: Any,
        score: Double
    ): Double? {
        return redisTemplate.opsForZSet().incrementScore(key, value, score)
    }

    fun rankZSet(
        key: String,
        value: Any
    ): Long? {
        return redisTemplate.opsForZSet().rank(key, value)
    }
}
