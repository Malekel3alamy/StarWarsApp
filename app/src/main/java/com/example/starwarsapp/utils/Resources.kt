package com.example.starwarsapp.utils

sealed class Resources <T> (val data :T? =null,
                          val message:String? =null) {

    class Loading<T>() : Resources<T>()
    class Success<T>( data:T) : Resources<T>(data)
    class Fail<T>( message:String,data: T?=null)    : Resources<T>(data,message)
}