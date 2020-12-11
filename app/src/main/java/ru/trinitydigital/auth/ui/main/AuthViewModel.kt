package ru.trinitydigital.auth.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.trinitydigital.auth.data.model.TokenModel
import ru.trinitydigital.auth.data.repositories.RetrofitRepository

class AuthViewModel(private val repository: RetrofitRepository) : ViewModel() {

    val actionNewScreen = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val result = repository.login(email = email, password = password)
            if (result.isSuccessful) actionNewScreen.postValue(true)
            else showError(result)
        }
    }

    private fun showError(result: Response<TokenModel>) {
        error.postValue("error")
    }
}