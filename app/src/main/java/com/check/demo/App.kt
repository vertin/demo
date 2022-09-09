package com.check.demo

import android.app.Application
import com.check.demo.di.ModuleManager
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                ModuleManager.modules
            )
        }
    }
}
