package evo.company.appcomponents.api

import evo.company.appcomponents.api.response.RepositoryResponse
import evo.company.appcomponents.model.Repository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Repository API client
 */
class RepoApiClient(retrofit: Retrofit) : RepoApi {

  interface Service {

    @GET("repos/{owner}/{repo}")
    fun getRepo(
      @Path("owner") owner: String,
      @Path("repo") repo: String
    ): Deferred<RepositoryResponse>
  }

  private val service = retrofit.create(Service::class.java)

  override suspend fun getRepo(owner: String, repo: String): Repository {
    return withContext(IO) {
      service.getRepo(owner, repo).await().toModel()
    }
  }
}