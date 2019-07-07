package evo.company.appcomponents.repository

import evo.company.appcomponents.api.SearchApi
import evo.company.appcomponents.model.SimpleDeveloper


/**
 * Repository for `SearchDevelopersViewModel`
 */
class SearchDevelopersRepository constructor(
  private val searchApi: SearchApi
) {

  suspend fun searchDevelopers(query: String): List<SimpleDeveloper> {
    return searchApi.searchDevelopers(query)
  }
}