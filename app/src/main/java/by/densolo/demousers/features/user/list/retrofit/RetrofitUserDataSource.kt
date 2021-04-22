package by.densolo.demousers.data.features.user.list.retrofit

import by.densolo.demousers.features.user.list.UserRemoteDataSource
import by.densolo.demousers.data.features.user.remote.UserApi
import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.Single
import javax.inject.Inject

class RetrofitUserDataSource @Inject constructor(private val userApi: UserApi): UserRemoteDataSource {

    override fun getUser(id: Int): Single<UserItem> = userApi.getUser(id)

    override fun getUserList(): Single<List<UserItem>> = userApi.getUserList()
}