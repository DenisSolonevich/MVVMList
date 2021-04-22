package by.densolo.demousers.screens.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.densolo.demousers.features.user.list.UserRepository
import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class UsersViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _userList = MutableLiveData<List<UserItem>>()
    val userList : LiveData<List<UserItem>>
        get() = _userList

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _currentStatus: MutableLiveData<String> = MutableLiveData()
    val currentStatus: LiveData<String>
        get() = _currentStatus

    init {
        compositeDisposable.add(getUsers())
    }

    private fun getUsers(): Disposable {
        _loadingState.value = true
        return userRepository.getUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _currentStatus.value = "onNext (network)"
                    _userList.value = it
                }, {
                    _currentStatus.value = "onError (network)"
                    _loadingState.value = false
                    Timber.e(it)
                }, {
                    _currentStatus.value = "onComplete (network)"
                    _loadingState.value = false
                }
                )
    }

    private fun getLocalUsers(): Disposable {
        _loadingState.value = true
        return userRepository.getLocalUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _currentStatus.value = "onNext (local)"
                    _userList.value = it
                }, {
                    _currentStatus.value = "onError (local)"
                    _loadingState.value = false
                    Timber.e(it)
                }, {
                    _currentStatus.value = "onComplete (local)"
                    _loadingState.value = false
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