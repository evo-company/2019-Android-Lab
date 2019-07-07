package evo.company.appcomponents.api

import evo.company.appcomponents.api.response.ListResponse
import evo.company.appcomponents.api.response.RepositoryResponse
import evo.company.appcomponents.api.response.SimpleDeveloperResponse
import evo.company.appcomponents.model.DateFormatters
import evo.company.appcomponents.model.Language
import evo.company.appcomponents.model.Repository
import evo.company.appcomponents.model.SimpleDeveloper
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Search API client
 */
class SearchApiClient(retrofit: Retrofit) : SearchApi {

    interface Service {

        @GET("search/repositories")
        fun getHotRepos(
            @Query("q") query: String,
            @Query("sort") sort: String = "stars"
        ): Deferred<ListResponse<RepositoryResponse>>

        @GET("search/users")
        fun getHotDevelopers(
            @Query("q") query: String,
            @Query("sort") sort: String = "stars"
        ): Deferred<ListResponse<SimpleDeveloperResponse>>

        @GET("search/repositories")
        fun searchRepos(
            @Query("q") query: String
        ): Deferred<ListResponse<RepositoryResponse>>

        @GET("search/users")
        fun searchDevelopers(
            @Query("q") query: String
        ): Deferred<ListResponse<SimpleDeveloperResponse>>
    }

    private val service = retrofit.create(Service::class.java)

    override suspend fun getHotRepos(
        language: Language,
        from: LocalDateTime
    ): List<Repository> {
        return withContext(IO) {
            service.getHotRepos("language:${language.title} created:>${from.format(DateFormatters.ofSearchQuery())}")
                .await()
                .items
                ?.map { response -> response.toModel() } ?: emptyList()
        }
    }

    override suspend fun getHotDevelopers(
        language: Language,
        from: LocalDateTime
    ): List<SimpleDeveloper> {
        return withContext(IO) {
            service.getHotDevelopers("language:${language.title} created:>${from.format(DateFormatters.ofSearchQuery())}")
                .await()
                .items
                ?.map { response -> response.toModel() } ?: emptyList()
        }
    }

    override suspend fun searchRepos(query: String): List<Repository> {
        return withContext(IO) {
            val q = if (query.isNotBlank()) {
                query
            } else {
                "created:>${LocalDateTime.now().minusDays(7).format(DateFormatters.ofSearchQuery())}"
            }
            service.searchRepos(q)
                .await()
                .items
                ?.map { response -> response.toModel() } ?: emptyList()
        }
    }

    override suspend fun searchDevelopers(query: String): List<SimpleDeveloper> {
        return withContext(IO) {
            val q = if (query.isNotBlank()) {
                query
            } else {
                "created:>${LocalDateTime.now().minusDays(7).format(DateFormatters.ofSearchQuery())}"
            }
            service.searchDevelopers(q)
                .await()
                .items
                ?.map { response -> response.toModel() } ?: emptyList()
        }
    }
}