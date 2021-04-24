package by.densolo.demousers.features.album.list.remote

import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface AlbumApi {
/* Mockend provides the next query params:
    _eq _ne
    equal, not equal

    _gt _lt
    greater than, lower than

    _order
    sort data (asc desc)

    limit offset
    use them to paginate your results */


//    @GET("albums")
//    @GET("posts")
    @GET("todos?userId_gt=1&id_gt=1")
    fun getAlbumList(): Single<List<AlbumItem>>

    /**
     * There is used "_lt - lower than" query to emulate multiple items with equal "userId"
     * "_gt" used to not include "0"
     */
    @GET("todos?userId_gt=1&id_gt=1")
    fun getAlbumsForUser(@Query("userId_lt") id: Int): Single<List<AlbumItem>>
}