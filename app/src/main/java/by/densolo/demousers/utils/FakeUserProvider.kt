package by.densolo.demousers.utils

import by.densolo.demousers.features.user.list.remote.UserItem
import kotlin.random.Random

/**
 * Was used to check the list
 */
object FakeUserProvider {

    fun getUsers() = listOf(
        UserItem(email = "",
                id = Random.nextInt(1000,100000),
                name = "J.D.",
                phone = "1234567",
                username = "jd",
                website = "dummy.site"
        ),
        UserItem(email = "",
                id = Random.nextInt(1000,100000),
                name = "Abraham",
                phone = "1234567",
                username = "abe",
                website = "dummy.site"
        ),
        UserItem(email = "",
                id = Random.nextInt(1000,100000),
                name = "Lui",
                phone = "1234567",
                username = "lulu",
                website = "dummy.site"
        ),
    )
}