package com.work.experion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.work.experion.viewModel.FeatureListViewModel

@Suppress("UNCHECKED_CAST")
class FeatureListVMFactory(private val featureListRepository: FeatureListRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeatureListViewModel(featureListRepository) as T
    }
}