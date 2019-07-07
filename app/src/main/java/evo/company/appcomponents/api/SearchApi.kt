package evo.company.appcomponents.api

import evo.company.appcomponents.model.Language
import evo.company.appcomponents.model.Repository
import evo.company.appcomponents.model.SimpleDeveloper
import org.threeten.bp.LocalDateTime

/**
 * Search API
 */
interface SearchApi {

  /**
   * get hot list of [Repository] from [Language] created after [from].
   */
  suspend fun getHotRepos(language: Language, from: LocalDateTime): List<Repository>

  /**
   * get hot list of [SimpleDeveloper] from [Language] created after [from].
   */
  suspend fun getHotDevelopers(language: Language, from: LocalDateTime): List<SimpleDeveloper>

  /**
   * search [Repository] from query.
   */
  suspend fun searchRepos(query: String): List<Repository>

  /**
   * search [SimpleDeveloper] from query.
   */
  suspend fun searchDevelopers(query: String): List<SimpleDeveloper>
}