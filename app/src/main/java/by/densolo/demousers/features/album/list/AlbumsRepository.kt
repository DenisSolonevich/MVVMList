package by.densolo.demousers.features.album.list

import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import by.densolo.demousers.features.album.list.room.mapToRemote
import io.reactivex.Flowable
import io.reactivex.Single
import timber.log.Timber

class AlbumsRepository(
    private val albumsLocalDataSource: AlbumLocalDataSource,
    private val albumsRemoteDataSource: AlbumRemoteDataSource
    ) {

    fun getAlbumsForUser(userId: Int): Flowable<List<AlbumItem>> {
        val remoteRequest = getRemoteAlbumsForUser(userId)
        val localRequest = getLocalAlbumsForUser(userId)

        return localRequest.toFlowable()
            .filter { it.isNotEmpty() }
            .switchIfEmpty(
                remoteRequest
                    .flatMap {
                        albumsLocalDataSource.saveRemoteAlbumList(it)
                            .andThen(Flowable.just(it))
                    }
            )
    }

    fun getAlbumList(): Flowable<List<AlbumItem>> {
        val remoteRequest = getRemoteAlbumList()
        val localRequest = getLocalAlbumList()

        return Single.concat(localRequest, remoteRequest)
    }

    fun getRemoteAlbumList(): Single<List<AlbumItem>> {
        return albumsRemoteDataSource.getAlbumList()
    }

    private fun getRemoteAlbumsForUser(userId: Int): Flowable<List<AlbumItem>> {
        return albumsRemoteDataSource.getAlbumsForUser(userId).toFlowable()
    }

    private fun getLocalAlbumsForUser(userId: Int): Single<List<AlbumItem>> {
        return albumsLocalDataSource.getAlbumsForUser(userId)
                .flatMap { entities ->
                    Timber.d("Loaded ${entities.size} albums from the database")
                    Single.just(
                            entities.map { it.mapToRemote() }
                    )
                }
    }

    private fun getLocalAlbumList(): Single<List<AlbumItem>> {
        return albumsLocalDataSource.getAlbumList()
                .flatMap { entities ->
                    Timber.d("Loaded ${entities.size} albums from the database")
                    Single.just(
                            entities.map { it.mapToRemote() }
                    )
                }
    }
}