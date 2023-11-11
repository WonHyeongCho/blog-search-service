package my.blogsearchservice.client

interface SearchClient {

    fun searchBlog(query: String, sort: String, page: Int, size: Int): String
}
