package com.example.starwarsapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


fun checkInternetConnection(context: Context):Boolean{

    (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
        val internetStatus = getNetworkCapabilities(activeNetwork)?.run {
          when{
            hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
              hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->true
              hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

              else -> {false}
          }
        }
        return internetStatus?:false
    }
}