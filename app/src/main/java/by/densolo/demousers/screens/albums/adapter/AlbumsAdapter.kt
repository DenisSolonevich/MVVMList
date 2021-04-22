package by.densolo.demousers.screens.albums.adapter

import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.densolo.demousers.features.album.list.retrofit.AlbumItem

class AlbumsAdapter(private val clickClickListener: AlbumClickListener): ListAdapter<AlbumItem, AlbumViewHolder>(
    AlbumDiffCallback()
), Filterable {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getFilter(): Filter {
        TODO("Not yet implemented")
    }
}



class AlbumDiffCallback : DiffUtil.ItemCallback<AlbumItem>() {
    override fun areItemsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AlbumItem, newItem: AlbumItem): Boolean {
        return oldItem == newItem
    }
}

class AlbumClickListener(val clickListener: (user: AlbumItem) -> Unit) {
    fun onClick(album: AlbumItem) = clickListener(album)
}