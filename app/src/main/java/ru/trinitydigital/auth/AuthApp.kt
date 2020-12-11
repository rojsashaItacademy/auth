package ru.trinitydigital.auth

import android.app.Application
import org.koin.android.ext.android.startKoin
import ru.trinitydigital.auth.data.local.PrefsHelper
import ru.trinitydigital.auth.di.appModules

class AuthApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
        PrefsHelper.init(this)
    }
}