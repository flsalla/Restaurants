package io.mohammedalaamorsi.restaurants

import android.app.Application
import io.mohammedalaamorsi.restaurants.di.appModule
import io.mohammedalaamorsi.restaurants.di.dataModule
import io.mohammedalaamorsi.restaurants.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                appModule,
                dataModule,
                domainModule,
            )
        }
    }
}
