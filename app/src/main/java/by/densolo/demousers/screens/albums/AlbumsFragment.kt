package by.densolo.demousers.screens.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import by.densolo.demousers.R
import by.densolo.demousers.databinding.AlbumsFragmentBinding
import by.densolo.demousers.di.ViewModelFactory
import by.densolo.demousers.screens.albums.adapter.AlbumsAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject

class AlbumsFragment : Fragment(R.layout.albums_fragment) {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AlbumsViewModel by viewModels { viewModelFactory }
    private val binding: AlbumsFragmentBinding by viewBinding()

    lateinit var albumsAdapter: AlbumsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}