package by.densolo.demousers.features.user.list

import by.densolo.demousers.features.user.list.remote.UserItem
import by.densolo.demousers.features.user.list.room.UserEntity
import io.reactivex.Completable
import io.reactivex.Single

interface UserLocalDataSource {

    fun getUser(id: Int): Single<UserEntity>
    fun getUserList(): Single<List<UserEntity>>
    fun saveRemoteUserList(userItems: List<UserItem>): Completable
}