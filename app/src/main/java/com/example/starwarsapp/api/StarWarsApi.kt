package com.example.starwarsapp.api

import com.example.starwarsapp.api.models.characters.CharactersResponse
import com.example.starwarsapp.api.models.ships.ShipsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StarWarsApi {

    @GET("people")
    suspend fun  getCharacters(
        @Query("page")
        page : Int =1
    ) :Response<CharactersResponse>

    @GET("starships")
   suspend fun getShips(
        @Query("page")
        page : Int =1
   ) : Response<ShipsResponse>



}