package ru.trinitydigital.auth.ui.newActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.trinitydigital.auth.data.model.ProfileModel
import ru.trinitydigital.auth.data.repositories.RetrofitRepository

class NewViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val error = MutableLiveData<String>()

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                repository.loadProfile()
            }.onFailure {
                error.postValue(it.localizedMessage)
            }
        }
    }

    fun getProfileModel() = repository.getProfile()
}