package com.work.experion

import com.work.experion.interfaces.AppInterfaceAPI
import com.work.experion.model.CityModel
import com.work.experion.model.FeatureModel
import retrofit2.Response

/**
 * Repository class.
 *
 * This class handles all the API functions and DB functions and returns their output.
 *
 * @param api is the object of AppInterfaceAPI Interface class.
 * @param featureDAO is the object of FeatureDAO class. It handles all the DB functions
 */
class FeatureListRepository(
    private val api: AppInterfaceAPI,
    private val featureDAO: FeatureDAO
) {
    /** API function. Its used for fetch data from a URL.
     * @return CityModel response data.
     */
    suspend fun doGetLocationDetails(): Response<CityModel> {
        return api.doGetLocationDetails()
    }

    /** DB function. Its used for fetch data from City Table.
     * @return CityModel object data.
     */
    fun getCityDataFromDB(): CityModel {
        return featureDAO.getCityDetails()
    }

    /** DB function. Its used for fetch data from Features Table.
     * @return FeatureModel list data.
     */
    fun getFeatureListFromDB(): ArrayList<FeatureModel?>? {
        return featureDAO.getFeatureList()
    }

}