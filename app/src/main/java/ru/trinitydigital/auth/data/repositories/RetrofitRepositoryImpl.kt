package ru.trinitydigital.auth.data.repositories

import androidx.lifecycle.LiveData
import retrofit2.Response
import ru.trinitydigital.auth.data.interactors.RetrofitInteractor
import ru.trinitydigital.auth.data.local.PrefsHelper
import ru.trinitydigital.auth.data.local.ProfileDao
import ru.trinitydigital.auth.data.model.ProfileModel
import ru.trinitydigital.auth.data.model.TokenModel

interface RetrofitRepository {
    suspend fun login(email: String, password: String): Response<TokenModel>
    suspend fun loadProfile()
    fun getProfile(): LiveData<ProfileModel>
}

class RetrofitRepositoryImpl(
    private val network: RetrofitInteractor,
    private val profileDao: ProfileDao
) : RetrofitRepository {

    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val result = network.login(email = email, password = password)

        result.body()?.token?.let { PrefsHelper.saveToken(it) }
        return result
    }

    override suspend fun loadProfile() {
        val result = network.getProfile()
        result.body()?.let { profileDao.saveProfile(it) }
    }

    override fun getProfile() = profileDao.getProfile()
}