package evo.company.appcomponents.api

import evo.company.appcomponents.model.Developer
import evo.company.appcomponents.model.Repository

/**
 * Developer API
 */
interface DeveloperApi {

  /**
   * get [Developer] from name.
   */
  suspend fun getDeveloper(name: String): Developer

  /**
   * get list of [Repository] of owner.
   */
  suspend fun getRepos(owner: String): List<Repository>
}