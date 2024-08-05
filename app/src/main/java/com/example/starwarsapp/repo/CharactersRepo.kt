package com.example.starwarsapp.repo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.starwarsapp.api.RetrofitImp
import com.example.starwarsapp.api.models.characters.Character
import com.example.starwarsapp.api.models.ships.Ship
import com.example.starwarsapp.db.ChAndShDatabase
import com.example.starwarsapp.pager.CharacterPagingSource

class CharactersRepo(val db:ChAndShDatabase) {

     // Api Data
    suspend fun getCharacters(pageNumber:Int) = RetrofitImp.api.getCharacters(pageNumber)

    // Room Database
    fun getAllCharacters() : LiveData<List<Character>> =db.getDao().getAllCharacters()




    suspend fun upsetCharacter(ch:Character) =db.getDao().upsertCharacter(ch)



    suspend fun deleteAllCharacters()  = db.getDao().deleteAllCharacters()

}