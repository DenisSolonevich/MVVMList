package by.densolo.demousers.features.user.list

import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.Single

interface UserRemoteDataSource {
    fun getUserList(): Single<List<UserItem>>
    fun getUser(id: Int): Single<UserItem>
}