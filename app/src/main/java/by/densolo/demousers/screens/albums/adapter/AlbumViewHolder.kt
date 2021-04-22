package by.densolo.demousers.screens.albums.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import by.densolo.demousers.databinding.AlbumItemLayoutBinding
import by.densolo.demousers.features.album.list.retrofit.AlbumItem
import by.kirich1409.viewbindingdelegate.viewBinding

class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewBinding: AlbumItemLayoutBinding by viewBinding()

    fun bind(album: AlbumItem) {
        with(viewBinding) {
            albumTitle.text = album.title
        }
    }
}