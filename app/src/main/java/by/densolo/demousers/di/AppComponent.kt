package by.densolo.demousers.di

import android.app.Application
import by.densolo.demousers.MainActivity
import by.densolo.demousers.features.user.list.UserDataModule
import by.densolo.demousers.features.album.list.AlbumsDataModule
import by.densolo.demousers.screens.users.UsersFragment
import by.densolo.demousers.screens.home.HomeFragment
import by.densolo.demousers.screens.about.AboutFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UserDataModule::class, AlbumsDataModule::class, DatabaseModule::class, NetworkModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: Application)

    fun inject(mainActivity: MainActivity)

    fun inject(usersFragment: UsersFragment)

    fun inject(homeFragment: HomeFragment)

    fun inject(notificationFragment: AboutFragment)

}