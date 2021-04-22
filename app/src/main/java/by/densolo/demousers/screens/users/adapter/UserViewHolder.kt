package by.densolo.demousers.screens.users.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.densolo.demousers.databinding.UserItemLayoutBinding
import by.densolo.demousers.features.user.list.remote.UserItem
import by.kirich1409.viewbindingdelegate.viewBinding

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding: UserItemLayoutBinding by viewBinding()

    fun bind(user: UserItem) {
        with(viewBinding) {
            userTitle.text = user.name
            userDetails.text = user.email
        }
    }
}