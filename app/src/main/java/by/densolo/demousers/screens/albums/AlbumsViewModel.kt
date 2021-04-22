package by.densolo.demousers.screens.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import by.densolo.demousers.features.album.list.AlbumsRepository
import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import by.densolo.demousers.features.user.list.UserRepository
import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(private val userRepository: UserRepository, private val albumRepository: AlbumsRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _albumList = MutableLiveData<List<AlbumItem>>()
    val albumList : LiveData<List<AlbumItem>>
        get() = _albumList

    private val _loadingState: MutableLiveData<Boolean> = MutableLiveData()
    val loadingState: LiveData<Boolean>
        get() = _loadingState

    private val _currentStatus: MutableLiveData<String> = MutableLiveData()
    val currentStatus: LiveData<String>
        get() = _currentStatus

    private val _user: MutableLiveData<UserItem> = MutableLiveData()
    val user: LiveData<UserItem>
        get() = _user

    private fun getUser(id: Int): Disposable {
        return userRepository.getUser(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _user.value = it
            }, {
                Timber.e(it)
            })
    }

    private fun getAlbums(userId: Int): Disposable {
        _loadingState.value = true
        return albumRepository.getAlbumsForUser(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _currentStatus.value = "onNext (local)"
                _albumList.value = it
                _loadingState.value = false
            }, {
                _currentStatus.value = "onError (local)"
                _loadingState.value = false
                Timber.e(it)
            }
            )
    }

    fun getUserAlbums(userId: Int) {
        getUser(userId)
        getAlbums(userId)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}