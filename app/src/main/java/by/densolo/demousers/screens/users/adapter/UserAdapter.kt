package by.densolo.demousers.screens.users.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.densolo.demousers.R
import by.densolo.demousers.features.user.list.remote.UserItem

class UserAdapter(private val clickClickListener: UserClickListener): ListAdapter<UserItem, UserViewHolder>(
    UserDiffCallback()
), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: UserViewHolder, position: Int) {
        viewHolder.itemView.setOnClickListener {
            clickClickListener.onClick(getItem(position))
        }
        viewHolder.bind(getItem(position))
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                return FilterResults().apply { values = currentList.filter { it.name.contains(constraint!!, true)} }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                submitList(results?.values as List<UserItem>)
            }
        }
    }
}

class UserDiffCallback : DiffUtil.ItemCallback<UserItem>() {
    override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
        return oldItem == newItem
    }
}

class UserClickListener(val clickListener: (user: UserItem) -> Unit) {
    fun onClick(user: UserItem) = clickListener(user)
}