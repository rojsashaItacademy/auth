package ru.trinitydigital.auth.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import ru.trinitydigital.auth.BuildConfig.BASE_URL
import ru.trinitydigital.auth.data.interactors.RetrofitInteractor
import ru.trinitydigital.auth.data.interactors.RetrofitInteractorImpl
import ru.trinitydigital.auth.data.remote.RetrofitBuilder
import ru.trinitydigital.auth.data.repositories.RetrofitRepository
import ru.trinitydigital.auth.data.repositories.RetrofitRepositoryImpl
import ru.trinitydigital.auth.ui.main.AuthViewModel
import ru.trinitydigital.auth.ui.newActivity.NewViewModel

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { NewViewModel(get()) }
}

val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) }
    single<RetrofitInteractor> { RetrofitInteractorImpl(get()) }
    single<RetrofitRepository> { RetrofitRepositoryImpl(get()) }
}

val appModules = listOf(viewModelModule, repositoryModule)