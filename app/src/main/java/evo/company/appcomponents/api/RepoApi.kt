package evo.company.appcomponents.api

import evo.company.appcomponents.model.Repository

/**
 * Repository API
 */
interface RepoApi {

  /**
   * get [Repository] from owner name and repo name.
   */
  suspend fun getRepo(owner: String, repo: String): Repository
}