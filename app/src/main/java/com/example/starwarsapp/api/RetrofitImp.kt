package com.example.starwarsapp.api

import com.example.starwarsapp.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitImp() {
companion object{

   private val retrofit by lazy {

        val logging = HttpLoggingInterceptor()
       logging.setLevel(HttpLoggingInterceptor.Level.BODY)

       val client = OkHttpClient.Builder()
           .addInterceptor(logging)
           .writeTimeout(10,TimeUnit.SECONDS)
           .readTimeout(10,TimeUnit.SECONDS)
           .connectTimeout(10,TimeUnit.SECONDS)
           .retryOnConnectionFailure(true)
           .build()

       Retrofit.Builder()
           .client(client)
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
           .build()
    }

    val api = retrofit.create(StarWarsApi::class.java)

}

}