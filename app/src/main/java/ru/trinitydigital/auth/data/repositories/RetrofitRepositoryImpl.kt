package ru.trinitydigital.auth.data.repositories

import retrofit2.Response
import ru.trinitydigital.auth.data.interactors.RetrofitInteractor
import ru.trinitydigital.auth.data.local.PrefsHelper
import ru.trinitydigital.auth.data.model.ProfileModel
import ru.trinitydigital.auth.data.model.TokenModel

interface RetrofitRepository {
    suspend fun login(email: String, password: String): Response<TokenModel>
    suspend fun getProfile(): Response<ProfileModel?>
}

class RetrofitRepositoryImpl(private val network: RetrofitInteractor) : RetrofitRepository {

    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val result = network.login(email = email, password = password)

//        result.body()?.token?.let { PrefsHelper.saveToken(it) }
        return result
    }

    override suspend fun getProfile(): Response<ProfileModel?> {
        return network.getProfile()
    }
}