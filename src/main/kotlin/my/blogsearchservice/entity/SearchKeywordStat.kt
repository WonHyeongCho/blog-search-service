package my.blogsearchservice.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import jakarta.validation.constraints.NotEmpty

@Entity
@Table(
    name = "search_keyword_stats",
    indexes = [Index(name = "idx_keyword", columnList = "keyword")]
)
class SearchKeywordStat(
    @Id
    @NotEmpty
    val keyword: String? = null,
    var count: Long = 1L
) {

    fun incrementCount() {
        count += 1L
    }
}
