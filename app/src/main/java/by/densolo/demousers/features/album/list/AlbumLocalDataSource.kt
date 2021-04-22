package by.densolo.demousers.features.album.list

import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import by.densolo.demousers.features.album.list.room.AlbumEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AlbumLocalDataSource {

    fun getAlbumList(): Single<List<AlbumEntity>>
    fun saveRemoteAlbumList(albumItems: List<AlbumItem>): Completable
}