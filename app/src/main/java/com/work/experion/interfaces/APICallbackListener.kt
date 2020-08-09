package com.work.experion.interfaces

import com.work.experion.model.CityModel

interface APICallbackListener{
    fun onResponseSuccess(response:CityModel)
    fun onResponseFailure()
    fun onResponseNull()
}