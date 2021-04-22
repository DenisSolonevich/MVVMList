package by.densolo.demousers.features.album.list

import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import io.reactivex.Single

interface AlbumRemoteDataSource {

    fun getAlbumList(): Single<List<AlbumItem>>
    fun getAlbumsForUser(userId: Int): Single<List<AlbumItem>>

}