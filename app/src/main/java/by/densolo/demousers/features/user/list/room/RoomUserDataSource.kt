package by.densolo.demousers.features.user.list.room

import by.densolo.demousers.features.user.list.UserLocalDataSource
import by.densolo.demousers.features.user.list.remote.UserItem
import io.reactivex.Completable
import io.reactivex.Single

class RoomUserDataSource(private val userDao: UserDao): UserLocalDataSource {

    override fun getUserList(): Single<List<UserEntity>> = userDao.getUserList()

    override fun saveRemoteUserList(userItems: List<UserItem>): Completable =
            userDao.addUserList(userItems.map {
                UserEntity(
                        email = it.email,
                        id = it.id,
                        name = it.name,
                        phone = it.phone,
                        username = it.username,
                        website = it.website
                )
            })
}