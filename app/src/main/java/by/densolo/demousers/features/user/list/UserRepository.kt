package by.densolo.demousers.features.user.list

import by.densolo.demousers.features.user.list.remote.UserItem
import by.densolo.demousers.features.user.list.room.mapToRemote
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber

class UserRepository (
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource
    ) {

    fun getUser(id: Int): Single<UserItem> {
        return userLocalDataSource.getUser(id)
            .flatMap {
                Single.just(
                    it.mapToRemote()
                )
            }
    }

    fun getUserList(): Observable<List<UserItem>> {
        val remoteRequest = getRemoteUserList()
        val localRequest = getLocalUserList()

        return Observable.concat(localRequest, remoteRequest)
    }

    private fun getRemoteUserList(): Observable<List<UserItem>> {
        return userRemoteDataSource.getUserList().toObservable()
                .flatMap {
                    Timber.e("#getRemoteUserList")
                    userLocalDataSource.saveRemoteUserList(it)
                            .andThen(Observable.just(it))
                }
    }

    fun getLocalUserList(): Observable<List<UserItem>> {
        return userLocalDataSource.getUserList().toObservable()
                .flatMap { entities ->
                    Timber.e("#getLocalUserList")
                    Observable.just(
                            entities.map { it.mapToRemote() }
                    )
                }
    }
}