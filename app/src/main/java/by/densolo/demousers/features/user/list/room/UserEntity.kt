package by.densolo.demousers.features.user.list.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import by.densolo.demousers.features.user.list.remote.UserItem
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity (
    // Not used in the demo to not spend time on unnecessary boilerplate conversions
//        @ColumnInfo(name = "address")
//        val address: Address,
//        @ColumnInfo(name = "company")
//        val company: Company,
        @ColumnInfo(name = "email")
        val email: String,
        @PrimaryKey @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "phone")
        val phone: String,
        @ColumnInfo(name = "username")
        val username: String,
        @ColumnInfo(name = "website")
        val website: String
): Parcelable {
    companion object {
        const val TABLE_NAME = "user"
    }
}

fun UserEntity.mapToRemote(): UserItem {
    return UserItem(
            id = this.id,
            email = this.email,
            name = this.name,
            phone = this.phone,
            username = this.username,
            website = this.website
    )
}