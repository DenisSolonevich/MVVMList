package by.densolo.demousers.features.user.list

import by.densolo.demousers.features.user.list.remote.UserItem
import by.densolo.demousers.features.user.list.room.mapToRemote
import io.reactivex.Observable
import timber.log.Timber

class UserRepository (
        private val userLocalDataSource: UserLocalDataSource,
        private val userRemoteDataSource: UserRemoteDataSource
    ) {

    fun getUserList(): Observable<List<UserItem>> {
        val remoteRequest = getRemoteUserList()
        val localRequest = getLocalUserList()

        return Observable.concat(localRequest, remoteRequest)
    }

    fun reloadUserList(): Observable<List<UserItem>> {
        return userRemoteDataSource.getUserList().toObservable()
                .flatMap {
                    Timber.e("#getRemoteUserList")
                    userLocalDataSource.saveRemoteUserList(it)
                            .andThen(Observable.just(it))
                }
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