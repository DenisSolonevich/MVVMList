package by.densolo.demousers.features.album.list.room

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import by.densolo.demousers.features.album.list.room.AlbumEntity.Companion.TABLE_NAME

@Parcelize
@Entity(tableName = TABLE_NAME)
data class AlbumEntity (
    @ColumnInfo(name = "userId") val userId: Int,
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "albumId") val id: Int,
    @ColumnInfo(name = "title") val title: String
): Parcelable {
    companion object {
        const val TABLE_NAME = "album"
    }
}

fun AlbumEntity.mapToRemote(): AlbumItem {
    return AlbumItem(
            userId = this.userId,
            id = this.id,
            title = this.title
    )
}