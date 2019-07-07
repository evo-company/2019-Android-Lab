package evo.company.appcomponents.ui.devDetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import evo.company.appcomponents.model.Developer
import evo.company.appcomponents.repository.DeveloperDetailRepository
import kotlinx.coroutines.launch

class DeveloperDetailViewModel constructor(
    private val developerDetailRepository: DeveloperDetailRepository
) : ViewModel() {

    private val developerResult = MutableLiveData<Developer>()

    val developer: LiveData<Developer> = developerResult

    init {
        viewModelScope.launch {
            runCatching {
                developerDetailRepository.getDeveloper("PythonBian")
            }.onSuccess { developer ->
                developerResult.value = developer
            }
        }
    }
}