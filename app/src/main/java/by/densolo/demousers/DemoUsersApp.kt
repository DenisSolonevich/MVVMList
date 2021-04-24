package by.densolo.demousers

import android.app.Application
import by.densolo.demousers.di.*
import timber.log.Timber

class DemoUsersApp: Application() {

    companion object{ lateinit var appComponent: AppComponent }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        Timber.plant(Timber.DebugTree())
    }
}