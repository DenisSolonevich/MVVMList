package by.densolo.demousers.features.album.list.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface AlbumDao {

    @Query("SELECT * FROM ${AlbumEntity.TABLE_NAME}")
    fun getAlbumList(): Single<List<AlbumEntity>>

    @Query("SELECT * FROM ${AlbumEntity.TABLE_NAME} WHERE :albumId = albumId")
    fun getAlbumList(albumId: Int): Single<List<AlbumEntity>>

    @Query("SELECT * FROM ${AlbumEntity.TABLE_NAME} WHERE :userId = userId")
    fun getAlbumsForUser(userId: Int): Single<List<AlbumEntity>>

    @Query("SELECT EXISTS (SELECT 1 FROM ${AlbumEntity.TABLE_NAME} WHERE :userId = userId)")
    fun hasAlbumsForUser(userId: Int): Single<Boolean>

    @Insert(entity = AlbumEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addAlbum(albumEntity: AlbumEntity): Completable

    @Insert(entity = AlbumEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addAlbumList(albumList: List<AlbumEntity>): Completable

    @Delete(entity = AlbumEntity::class)
    fun deleteAlbum(albumEntity: AlbumEntity): Completable
}