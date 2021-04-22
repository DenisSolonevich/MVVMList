package by.densolo.demousers

import android.app.Application
import by.densolo.demousers.di.*
import by.densolo.demousers.features.album.list.AlbumsDataModule
import by.densolo.demousers.features.user.list.UserDataModule
import timber.log.Timber

class DemoUsersApp: Application() {

    companion object{ lateinit var appComponent: AppComponent }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .networkModule(NetworkModule())
            .appModule(AppModule(this))
            .albumsDataModule(AlbumsDataModule())
            .databaseModule(DatabaseModule())
            .userDataModule(UserDataModule())
            .build()

        Timber.plant(Timber.DebugTree())
    }
}