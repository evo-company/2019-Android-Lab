package evo.company.appcomponents.repository

import evo.company.appcomponents.api.DeveloperApi
import evo.company.appcomponents.model.Developer
import evo.company.appcomponents.model.Repository

/**
 * Repository for `DeveloperDetailViewModel`
 */
class DeveloperDetailRepository  constructor(
  private val developerApi: DeveloperApi
) {

  suspend fun getDeveloper(name: String): Developer {
    return developerApi.getDeveloper(name)
  }

  suspend fun getRepos(owner: String): List<Repository> {
    return developerApi.getRepos(owner)
  }
}