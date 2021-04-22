package by.densolo.demousers.data.features.user.remote

import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AlbumApi {
    // https://jsonplaceholder.typicode.com/albums
    @GET("albums/")
    fun getAlbumList(): Single<List<AlbumItem>>

    @GET("albums?userId={id}/")
    fun getAlbumsForUser(@Path("id") id: Int): Single<List<AlbumItem>>
}