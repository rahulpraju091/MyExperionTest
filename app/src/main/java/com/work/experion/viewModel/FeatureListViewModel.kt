package com.work.experion.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.work.experion.FeatureListRepository
import com.work.experion.common.Coroutines
import com.work.experion.interfaces.APICallbackListener
import com.work.experion.model.FeatureModel

class FeatureListViewModel(private var featureListRepository: FeatureListRepository) : ViewModel() {

    companion object {
        private val TAG: String = FeatureListViewModel::class.java.simpleName.toString()
    }

    private var _mutableTitle = MutableLiveData<String?>()
    val title: LiveData<String?>?
        get() = _mutableTitle
    private var _mutableFeaturesList = MutableLiveData<ArrayList<FeatureModel?>>()
    val featuresList: LiveData<ArrayList<FeatureModel?>>?
        get() = _mutableFeaturesList

    fun doGetLocationDetails(listener: APICallbackListener) {
        Coroutines.main {
            val response = featureListRepository.doGetLocationDetails()
            Log.d(TAG, "Response:::" + response.body().toString())
            if (response.body()?.rows.isNullOrEmpty()) {
                listener.onResponseFailure()
            } else if (response.isSuccessful && response.body()?.rows!!.isNotEmpty()) {
                _mutableTitle.value = response.body()!!.title
                _mutableFeaturesList.value = response.body()!!.rows
                listener.onResponseSuccess(response.body()!!)
            } else {
                listener.onResponseNull()
            }
        }
    }


}