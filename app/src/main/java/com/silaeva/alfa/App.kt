package com.silaeva.alfa

import android.app.Application
import com.silaeva.alfa.data.repository_impl.HistoryRepositoryImpl
import com.silaeva.alfa.data.repository_impl.InfoRepositoryImpl
import com.silaeva.alfa.domain.di.interactorModule
import com.silaeva.alfa.domain.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        HistoryRepositoryImpl.initialize(this)
        InfoRepositoryImpl.initialize(this)

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(presentationModule, interactorModule)
        }
    }
}