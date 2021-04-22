package by.densolo.demousers.features.album.list.room

import by.densolo.demousers.features.album.list.AlbumLocalDataSource
import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import io.reactivex.Completable
import io.reactivex.Single

class RoomAlbumDataSource(private val albumDao: AlbumDao): AlbumLocalDataSource {

    override fun getAlbumList(): Single<List<AlbumEntity>> = albumDao.getAlbumList()

    override fun getAlbumsForUser(userId: Int): Single<List<AlbumEntity>> = albumDao.getAlbumsForUser(userId)

    override fun saveRemoteAlbumList(albumItems: List<AlbumItem>): Completable =
            albumDao.addAlbumList(albumItems.map {
                AlbumEntity(
                        userId = it.userId,
                        id = it.id,
                        title = it.title
                )
            })
}