package by.densolo.demousers.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider


/**
 * TODO !!NOTE!! This part was taken from the third-party solution. I'm not familiar for 100% not exactly going on here.
 * TODO Study it
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {
    private val usedViewModelMap = HashMap<Class<out ViewModel>, ViewModel>()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (usedViewModelMap.contains(modelClass)) {
            return usedViewModelMap[modelClass] as T
        }
        val viewModelProvider = viewModels[modelClass] ?: throw IllegalArgumentException(" model class $modelClass not found")

        val viewModel = viewModelProvider.get()
        usedViewModelMap[viewModel::class.java] = viewModel

        return viewModel as T
    }
}