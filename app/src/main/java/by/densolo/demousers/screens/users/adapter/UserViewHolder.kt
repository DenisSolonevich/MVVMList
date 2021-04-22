package by.densolo.demousers.screens.users.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.densolo.demousers.databinding.ItemLayoutBinding
import by.densolo.demousers.features.user.list.remote.UserItem
import by.kirich1409.viewbindingdelegate.viewBinding

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding: ItemLayoutBinding by viewBinding()

    fun bind(user: UserItem) {
        with(viewBinding) {
            tvAlbumTitle.text = user.name
            tvDetails.text = user.email
        }
    }
}