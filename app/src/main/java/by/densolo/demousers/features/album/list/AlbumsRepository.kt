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

//    fun getAlbumList(): Observable<List<AlbumItem>> {
    fun getAlbumList(): Flowable<List<AlbumItem>> {
        val remoteRequest = getRemoteAlbumList()
        val localRequest = getLocalAlbumList()

        return Single.concat(localRequest, remoteRequest)
    }

//    private fun getRemoteAlbumList(): Observable<List<AlbumItem>> {
    private fun getRemoteAlbumList(): Single<List<AlbumItem>> {
//        return albumsRemoteDataSource.getAlbumList().toObservable()
        return albumsRemoteDataSource.getAlbumList()
                .flatMap {
                    Timber.d("Prepared ${it.size} albums for the database")
                    albumsLocalDataSource.saveRemoteAlbumList(it)
//                            .andThen(Observable.just(it))
                            .andThen(Single.just(it))
                }
    }

//    private fun getLocalAlbumList(): Observable<List<AlbumItem>> {
    private fun getLocalAlbumList(): Single<List<AlbumItem>> {
//        return albumsLocalDataSource.getAlbumList().toObservable()
        return albumsLocalDataSource.getAlbumList()
                .flatMap { entities ->
                    Timber.d("Loaded ${entities.size} albums from the database")
                    Single.just(
                            entities.map { it.mapToRemote() }
                    )
                }
    }
}