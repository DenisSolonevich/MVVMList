package by.densolo.demousers.screens.albums.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.densolo.demousers.R
import by.densolo.demousers.features.album.list.retrofit.AlbumItem

class AlbumAdapter(): ListAdapter<AlbumItem, AlbumViewHolder>(
    AlbumDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            LayoutInflater.from(parent.context)
            .inflate(R.layout.album_item_layout, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: AlbumViewHolder, position: Int) {
        viewHolder.bind(getItem(position))
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