package by.densolo.demousers.features.album.list.retrofit

import by.densolo.demousers.data.features.user.remote.AlbumApi
import by.densolo.demousers.features.album.list.AlbumRemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class RetrofitAlbumDataSource @Inject constructor(private val albumApi: AlbumApi): AlbumRemoteDataSource {

    override fun getAlbumList(): Single<List<AlbumItem>> = albumApi.getAlbumList()
    override fun getAlbumsForUser(userId: Int): Single<List<AlbumItem>> = albumApi.getAlbumsForUser(userId)

}