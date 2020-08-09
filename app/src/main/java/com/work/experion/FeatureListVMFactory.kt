package com.work.experion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.work.experion.viewModel.FeatureListViewModel

/**
 * ViewModel Factory class.
 *
 * Using this factory class we achieve dependency injection
 * @param featureListRepository is the Repository class object.
 */
@Suppress("UNCHECKED_CAST")
class FeatureListVMFactory(private val featureListRepository: FeatureListRepository) : ViewModelProvider.NewInstanceFactory() {

    /** Override function.
     * @return FeatureListViewModel instance.
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeatureListViewModel(featureListRepository) as T
    }
}