package com.work.experion.interfaces

import com.work.experion.model.CityModel

/** Interface for handle API callback.*/
interface APICallbackListener {
    fun onResponseSuccess(response: CityModel)
    fun onResponseFailure()
    fun onResponseNull()
}