package by.densolo.demousers.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideApplication() = app

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = this.app

}