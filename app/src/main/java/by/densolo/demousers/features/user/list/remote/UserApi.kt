package by.densolo.demousers.data.features.user.remote

import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {
    // https://jsonplaxceholder.typicode.com/users
    @GET("users/")
    fun getUserList(): Single<List<UserItem>>
}