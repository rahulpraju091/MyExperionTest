package com.work.experion.viewModel

import androidx.lifecycle.ViewModel
import com.work.experion.FeatureListRepository

class FeatureListViewModel(private var featureListRepository: FeatureListRepository) : ViewModel() {

    companion object {
        private val TAG: String = FeatureListViewModel::class.java.simpleName.toString()
    }

}