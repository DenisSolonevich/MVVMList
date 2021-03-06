package by.densolo.demousers.di

import by.densolo.demousers.features.album.list.remote.AlbumApi
import by.densolo.demousers.data.features.user.remote.UserApi
import by.densolo.demousers.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Nice placeholder, but not working now error 520 (22.04.2021)
private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
// Flexible placeholder with generated data
private const val BASE_URL_MOCKEND = "https://mockend.com/DenisSolonevich/mockend_test/"

@Module
open class NetworkModule {

    @Provides
    @Reusable
    internal fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    open fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_MOCKEND)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @Reusable
    internal fun provideAlbumApi(retrofit: Retrofit): AlbumApi = retrofit.create(AlbumApi::class.java)

    @Provides
    @Reusable
    internal fun provideUserApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)
}