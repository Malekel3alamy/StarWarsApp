package com.example.starwarsapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.starwarsapp.api.models.characters.Character
import com.example.starwarsapp.api.models.characters.CharactersResponse
import com.example.starwarsapp.api.models.ships.Ship
import com.example.starwarsapp.api.models.ships.ShipsResponse
import com.example.starwarsapp.pager.ShipsPagingSource
import com.example.starwarsapp.repo.ShipsRepo
import com.example.starwarsapp.utils.Resources
import kotlinx.coroutines.launch
import retrofit2.Response

class ShipsViewModel(val shipsRepo : ShipsRepo) : ViewModel(){


    val loading = MutableLiveData<Boolean>()

    val shipsList = Pager(PagingConfig(1)){
        ShipsPagingSource(shipsRepo)

    }.flow.cachedIn(viewModelScope)


    fun getAllShipsFromRoom() = shipsRepo.getAllShips()

    fun upsertShip(sh: Ship) =viewModelScope.launch {
        shipsRepo.upsertShip(sh)
    }

}