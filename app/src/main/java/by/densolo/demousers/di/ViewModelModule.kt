package by.densolo.demousers.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.densolo.demousers.screens.users.UsersViewModel
import by.densolo.demousers.screens.about.AboutViewModel
import by.densolo.demousers.screens.albums.AlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UsersViewModel::class)
    internal abstract fun bindUsersViewModel(viewModel: UsersViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    internal abstract fun bindAboutViewModel(viewModel: AboutViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    internal abstract fun bindAlbumsViewModel(viewModel: AlbumsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}