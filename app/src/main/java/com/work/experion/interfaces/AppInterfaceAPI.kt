package com.work.experion.interfaces

import com.work.experion.model.CityModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AppInterfaceAPI {

    companion object {
        operator fun invoke(): AppInterfaceAPI {
            val intecepter = HttpLoggingInterceptor()
            intecepter.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(intecepter)
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
    @FormUrlEncoded
    suspend fun doGetLocationDetails(): Response<CityModel>



}