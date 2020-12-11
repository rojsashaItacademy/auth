package ru.trinitydigital.auth.ui.newActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.trinitydigital.auth.data.model.ProfileModel
import ru.trinitydigital.auth.data.repositories.RetrofitRepository

class NewViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val data = MutableLiveData<ProfileModel>()
    val error = MutableLiveData<String>()

    fun loadUser() {
        viewModelScope.launch {
            runCatching {
                val result = repository.getProfile()
                if (result.isSuccessful)
                    data.postValue(result.body())
                else error.postValue(result.message())
            }.onFailure {
                error.postValue(it.localizedMessage)
            }
        }
    }
}