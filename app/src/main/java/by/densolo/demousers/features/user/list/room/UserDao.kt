package by.densolo.demousers.features.user.list.room

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM ${UserEntity.TABLE_NAME}")
    fun getUserList(): Single<List<UserEntity>>

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} WHERE :userId = id")
    fun getUserList(userId: Int): Single<List<UserEntity>>

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userEntity: UserEntity): Completable

    @Insert(entity = UserEntity::class, onConflict = OnConflictStrategy.REPLACE)
    fun addUserList(userList: List<UserEntity>): Completable

    @Delete(entity = UserEntity::class)
    fun deleteUser(userEntity: UserEntity): Completable
}