package by.densolo.demousers.di

import android.app.Application
import androidx.room.Room
import by.densolo.demousers.DemoUsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application) =
        Room.databaseBuilder(
            context,
            DemoUsersDatabase::class.java,
            "demo_users_database"
        ).build()

}