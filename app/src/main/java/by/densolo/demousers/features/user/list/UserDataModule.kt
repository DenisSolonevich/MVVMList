package by.densolo.demousers.features.user.list

import by.densolo.demousers.data.features.user.list.retrofit.RetrofitUserDataSource
import by.densolo.demousers.features.user.list.room.RoomUserDataSource
import by.densolo.demousers.data.features.user.remote.UserApi
import by.densolo.demousers.DemoUsersDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserDataModule {

    @Provides
    @Singleton
    fun provideLocalDataSource(roomDatabase: DemoUsersDatabase): UserLocalDataSource = RoomUserDataSource(roomDatabase.userDao())

    @Provides
    @Singleton
    fun provideRemoteDataSource(userApi: UserApi): UserRemoteDataSource = RetrofitUserDataSource(userApi)


    @Provides
    @Singleton
    fun provideUserListRepository(
            localDataSource: UserLocalDataSource,
            remoteDataSource: UserRemoteDataSource
    ): UserRepository =
        UserRepository(localDataSource, remoteDataSource)

}