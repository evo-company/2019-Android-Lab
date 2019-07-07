package evo.company.appcomponents.ui.devs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evo.company.appcomponents.model.SimpleDeveloper
import evo.company.appcomponents.repository.SearchDevelopersRepository
import kotlinx.coroutines.launch

class DevelopersViewModel constructor(
    private val searchDevelopersRepository: SearchDevelopersRepository
) : ViewModel() {

    private val searchedDevelopersResult = MutableLiveData<List<SimpleDeveloper>>()

    val searchedDevelopers: LiveData<List<SimpleDeveloper>> = searchedDevelopersResult

    init {
        viewModelScope.launch {
            runCatching {
                searchDevelopersRepository.searchDevelopers("")
            }.onSuccess { developers ->
                searchedDevelopersResult.value = developers
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            runCatching {
                searchDevelopersRepository.searchDevelopers(query)
            }.onSuccess { developers ->
                searchedDevelopersResult.value = developers
            }
        }
    }
}