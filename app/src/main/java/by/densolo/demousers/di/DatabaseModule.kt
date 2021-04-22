package by.densolo.demousers.di

import android.content.Context
import androidx.room.Room
import by.densolo.demousers.DemoUsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(
            context,
            DemoUsersDatabase::class.java,
            "demo_users_database"
        ).build()

}