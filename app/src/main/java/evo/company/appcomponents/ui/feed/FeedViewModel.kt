package evo.company.appcomponents.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evo.company.appcomponents.model.Language
import evo.company.appcomponents.model.Repository
import evo.company.appcomponents.repository.FeedRepository
import kotlinx.coroutines.launch

class FeedViewModel(
    private val feedRepository: FeedRepository
) : ViewModel() {

    private val mutableHotReposMap = MutableLiveData<List<Repository>>()

    fun getRepos(): LiveData<List<Repository>> {
        return mutableHotReposMap
    }

    init {
        viewModelScope.launch {
            runCatching {
                feedRepository.getHotRepos(Language.KOTLIN)
            }.onSuccess { map ->
                mutableHotReposMap.value = map
            }.onFailure {
                println("failed")
            }
        }
    }
}