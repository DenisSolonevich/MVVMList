package by.densolo.demousers

import androidx.room.Database
import androidx.room.RoomDatabase
import by.densolo.demousers.features.album.list.room.AlbumEntity
import by.densolo.demousers.features.album.list.room.AlbumDao
import by.densolo.demousers.features.user.list.room.*

@Database(
    entities = [
        AlbumEntity::class,
        UserEntity::class,
    ], version = 1, exportSchema = true
)
abstract class DemoUsersDatabase: RoomDatabase() {

    abstract fun albumDao(): AlbumDao
    abstract fun userDao(): UserDao
}