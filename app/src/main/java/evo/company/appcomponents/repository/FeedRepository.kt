package evo.company.appcomponents.repository

import evo.company.appcomponents.api.SearchApi
import evo.company.appcomponents.model.Language
import evo.company.appcomponents.model.Repository
import org.threeten.bp.LocalDateTime

/**
 * Repository for `FeedViewModel`
 */
class FeedRepository constructor(
    private val searchApi: SearchApi
) {

    suspend fun getHotRepos(language: Language): List<Repository> {
        return searchApi.getHotRepos(language, LocalDateTime.now().minusMonths(1))
    }
}