package com.work.experion

import com.work.experion.interfaces.AppInterfaceAPI
import com.work.experion.model.CityModel
import retrofit2.Response

class FeatureListRepository(private val api: AppInterfaceAPI) {

    suspend fun doGetLocationDetails(): Response<CityModel> {
        return api.doGetLocationDetails()
    }

}