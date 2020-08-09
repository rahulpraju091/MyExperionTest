package com.work.experion.viewModel

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.work.experion.FeatureListRepository
import com.work.experion.common.Coroutines
import com.work.experion.interfaces.APICallbackListener
import com.work.experion.model.FeatureModel

/**
 * ViewModel class.
 *
 * This class handles all actions from View and calls repository functions.
 *
 * @param _mutableTitle is a mutable live data of String type to keep title.
 * @param _mutableFeaturesList is a mutable live data of Object ArrayList type to keep Feature list.
 * @param title is a public live data of string type.
 * @param featuresList iis a public live data of Object ArrayList type.
 */
class FeatureListViewModel(private var featureListRepository: FeatureListRepository) : ViewModel() {

    private var _mutableTitle = MutableLiveData<String?>()
    val title: LiveData<String?>?
        get() = _mutableTitle
    private var _mutableFeaturesList = MutableLiveData<ArrayList<FeatureModel?>>()
    val featuresList: LiveData<ArrayList<FeatureModel?>>?
        get() = _mutableFeaturesList

    /** API function. Its used for fetch data from a URL. And listener is used for make callbacks
     * It sets the result into mutable live data
     */
    fun doGetLocationDetails(listener: APICallbackListener) {
        Coroutines.main {
            val response = featureListRepository.doGetLocationDetails()
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

    /** DB function. Its used for fetch data from DB and set the result into mutable live data.
     * Both title and feature list values are updating here.
     */
    fun doGetDataFromDB() {
        val cityDetails = featureListRepository.getCityDataFromDB()
        cityDetails.let {
            _mutableTitle.value = cityDetails.title
        }
        val featureList = featureListRepository.getFeatureListFromDB()
        featureList.let {
            _mutableFeaturesList.value = featureList
        }
    }
}