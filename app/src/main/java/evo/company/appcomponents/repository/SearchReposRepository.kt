package evo.company.appcomponents.repository

import evo.company.appcomponents.api.SearchApi
import evo.company.appcomponents.model.Repository

/**
 * Repository for `SearchReposViewModel`
 */
class SearchReposRepository constructor(
  private val searchApi: SearchApi
) {

  suspend fun searchRepos(query: String): List<Repository> {
    return searchApi.searchRepos(query)
  }
}