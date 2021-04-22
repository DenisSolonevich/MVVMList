package by.densolo.demousers.screens.albums

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.densolo.demousers.DemoUsersApp
import by.densolo.demousers.R
import by.densolo.demousers.databinding.FragmentAlbumsBinding
import by.densolo.demousers.di.ViewModelFactory
import by.densolo.demousers.screens.albums.adapter.AlbumAdapter
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject

class AlbumsFragment : Fragment(R.layout.fragment_albums) {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: AlbumsViewModel by viewModels { viewModelFactory }
    private val binding: FragmentAlbumsBinding by viewBinding()
    val args: AlbumsFragmentArgs by navArgs()

    lateinit var albumAdapter: AlbumAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        DemoUsersApp.appComponent.inject(this)

        albumAdapter = AlbumAdapter()

        viewModel.albumList.observe(viewLifecycleOwner,
            {
                albumAdapter.submitList(it)
            }
        )

        viewModel.user.observe(viewLifecycleOwner, {
            it?.let {
                binding.userInfo.userTitle.text = it.name
                binding.userInfo.userDetails.text = it.username
            }
        })

        with (binding.albumView) {
            layoutManager = LinearLayoutManager(context)
            adapter = albumAdapter
        }

        val statusObserver = Observer<String> { newName ->
            binding.statusLine.displayStatus.text = newName
        }

        val loadingObserver = Observer<Boolean> { loading ->
            if (loading) {
                binding.statusLine.root.visibility = View.VISIBLE
            } else {
                binding.statusLine.root.visibility = View.GONE
            }
        }

        viewModel.currentStatus.observe(viewLifecycleOwner, statusObserver)
        viewModel.loadingState.observe(viewLifecycleOwner, loadingObserver)
        viewModel.getUserAlbums(args.id)
    }
}