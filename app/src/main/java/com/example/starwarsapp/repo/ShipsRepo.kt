package com.example.starwarsapp.repo

import androidx.lifecycle.LiveData
import com.example.starwarsapp.api.RetrofitImp
import com.example.starwarsapp.api.StarWarsApi
import com.example.starwarsapp.api.models.ships.Ship
import com.example.starwarsapp.db.ChAndShDatabase


class ShipsRepo (val db: ChAndShDatabase){
    // Api Data
    suspend fun getShips(pageNumber : Int) = RetrofitImp.api.getShips(pageNumber)


    // Room Database
    fun getAllShips() : LiveData<List<Ship>> = db.getDao().getAllShips()
    suspend fun upsertShip(sh:Ship) = db.getDao().upsertShip(sh)
}