package by.densolo.demousers.screens.users

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.densolo.demousers.features.album.list.AlbumsRepository
import by.densolo.demousers.features.user.list.UserRepository
import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


/**
 * It has unused param for the reason of testing.
 */
class UsersViewModel @Inject constructor(private val albumRepository: AlbumsRepository, private val userRepository: UserRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val userList : MutableLiveData<List<UserItem>> = MutableLiveData()
    val loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val currentStatus: MutableLiveData<String> = MutableLiveData()

    init {
        compositeDisposable.add(getUsers())
    }

    private fun getUsers(): Disposable {
        loadingState.value = true
        return userRepository.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currentStatus.value = "onNext"
                    userList.value = it
                }, {
                    currentStatus.value = "onError"
                    Timber.e(it)
                    loadingState.value = false
                }, {
                    currentStatus.value = "onComplete"
                    loadingState.value = false
                }
                )
    }

    private fun getLocalUsers(): Disposable {
        loadingState.value = true
        return userRepository.getLocalUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currentStatus.value = "onNext"
                    userList.value = it
                }, {
                    currentStatus.value = "onError"
                    Timber.e(it)
                    loadingState.value = false
                }, {
                    currentStatus.value = "onComplete"
                    loadingState.value = false
                }
                )
    }

    fun loadUsers() {
        compositeDisposable.add(getUsers())
    }

    fun loadLocalUsers() {
        compositeDisposable.add(getLocalUsers())
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}