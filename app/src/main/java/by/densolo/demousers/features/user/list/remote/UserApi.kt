package by.densolo.demousers.data.features.user.remote

import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {
    /**
     * @see AlbumApi to read the documentation for the reason of _gt postfix
     */
    @GET("users?id_gt=1")
    fun getUserList(): Single<List<UserItem>>

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Single<UserItem>
}