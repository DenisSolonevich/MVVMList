package by.densolo.demousers.features.album.list

import by.densolo.demousers.features.album.list.remote.AlbumApi
import by.densolo.demousers.DemoUsersDatabase
import by.densolo.demousers.features.album.list.retrofit.RetrofitAlbumDataSource
import by.densolo.demousers.features.album.list.room.RoomAlbumDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AlbumsDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(roomDatabase: DemoUsersDatabase): AlbumLocalDataSource = RoomAlbumDataSource(roomDatabase.albumDao())

    @Provides
    @Singleton
    fun provideRemoteDataSource(albumApi: AlbumApi): AlbumRemoteDataSource = RetrofitAlbumDataSource(albumApi)

    @Provides
    @Singleton
    fun provideAlbumsRepository(
        localDataSource: AlbumLocalDataSource,
        remoteDataSource: AlbumRemoteDataSource
    ): AlbumsRepository =
        AlbumsRepository(localDataSource, remoteDataSource)


}