package ru.trinitydigital.auth.data.interactors

import retrofit2.Response
import ru.trinitydigital.auth.data.local.PrefsHelper
import ru.trinitydigital.auth.data.model.ProfileModel
import ru.trinitydigital.auth.data.model.TokenModel
import ru.trinitydigital.auth.data.remote.RetrofitService

interface RetrofitInteractor {
    suspend fun login(email: String, password: String): Response<TokenModel>
    suspend fun getProfile(): Response<ProfileModel?>
}

class RetrofitInteractorImpl(private val service: RetrofitService) : RetrofitInteractor {

    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val map = mapOf(
            "email" to email,
            "password" to password
        )

        return service.login(map)
    }

    override suspend fun getProfile(): Response<ProfileModel?> {
        return service.loadUserProfile()
    }
}