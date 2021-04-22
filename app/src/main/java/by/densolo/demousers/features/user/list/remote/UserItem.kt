package by.densolo.demousers.features.user.list.remote
import com.google.gson.annotations.SerializedName


data class UserItem(
        @SerializedName("address")
    val address: Address = Address("Paradise City", Geo("0.0","0.0"), "Baker Street", "221b", "000000"),
        @SerializedName("company")
    val company: Company = Company("-", "-", "Dr. Watson and Co."),
        @SerializedName("email")
    val email: String,
        @SerializedName("id")
    val id: Int,
        @SerializedName("name")
    val name: String,
        @SerializedName("phone")
    val phone: String,
        @SerializedName("username")
    val username: String,
        @SerializedName("website")
    val website: String
)

data class Address(
        @SerializedName("city")
    val city: String,
        @SerializedName("geo")
    val geo: Geo,
        @SerializedName("street")
    val street: String,
        @SerializedName("suite")
    val suite: String,
        @SerializedName("zipcode")
    val zipcode: String
)

data class Company(
    @SerializedName("bs")
    val bs: String,
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @SerializedName("name")
    val name: String
)

data class Geo(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String
)