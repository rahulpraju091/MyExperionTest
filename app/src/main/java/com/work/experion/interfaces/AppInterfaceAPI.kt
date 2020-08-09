package com.work.experion.interfaces

import com.work.experion.model.CityModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * Interface
 *
 * This class handles all the API functions and DB functions and returns their output.
 *
 * @param api is the object of AppInterfaceAPI Interface class.
 * @param featureDAO is the object of FeatureDAO class. It handles all the DB functions
 */
interface AppInterfaceAPI {

    companion object {
        operator fun invoke(): AppInterfaceAPI {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(AppInterfaceAPI::class.java)
        }
    }

    @GET("s/2iodh4vg0eortkl/facts.json")
    suspend fun doGetCityDetails(): Response<CityModel>


}