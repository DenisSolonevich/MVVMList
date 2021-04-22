package by.densolo.demousers.screens.users

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.densolo.demousers.screens.users.adapter.UserAdapter
import by.densolo.demousers.screens.users.adapter.UserClickListener
import by.densolo.demousers.DemoUsersApp
import by.densolo.demousers.R
import by.densolo.demousers.databinding.FragmentUsersBinding
import by.densolo.demousers.di.ViewModelFactory
import by.kirich1409.viewbindingdelegate.viewBinding
import javax.inject.Inject

class UsersFragment : Fragment(R.layout.fragment_users) {

    @Inject lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: UsersViewModel by viewModels { viewModelFactory }
    private val binding: FragmentUsersBinding by viewBinding()


    lateinit var userAdapter: UserAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        DemoUsersApp.appComponent.inject(this)

        userAdapter = UserAdapter(UserClickListener {
            findNavController().navigate(
                UsersFragmentDirections.actionNavigationUsersToAlbumsFragment(it.id)
            )
        })

        viewModel.userList.observe(viewLifecycleOwner, {
            it?.let {
                userAdapter.putUsers(it)
            }
        })

        with (binding.usersView) {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
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
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.users_menu, menu);
        val searchView = menu.findItem(R.id.user_search).actionView as SearchView
        with(searchView) {
            queryHint = "Search"
            setOnQueryTextListener(
                    object : SearchView.OnQueryTextListener {
                        override fun onQueryTextSubmit(constraint: String?): Boolean {
                            userAdapter.filter.filter(constraint)
                            return false
                        }

                        override fun onQueryTextChange(constraint: String?): Boolean {
                            userAdapter.filter.filter(constraint)
                            return false
                        }
                    }
            )
            setOnCloseListener {
                viewModel.userList.value?.let { userAdapter.putUsers(it) }
                false
            }
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.refresh -> {
                viewModel.loadUsers()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}