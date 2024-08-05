package com.example.starwarsapp.ui.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.starwarsapp.api.models.characters.Character
import com.example.starwarsapp.api.models.characters.CharactersResponse
import com.example.starwarsapp.pager.CharacterPagingSource
import com.example.starwarsapp.repo.CharactersRepo
import com.example.starwarsapp.utils.Resources
import kotlinx.coroutines.launch
import retrofit2.Response

class CharactersViewModel(val charactersRepo: CharactersRepo) :ViewModel(){



    val loading = MutableLiveData<Boolean>()


   val charactersList = Pager(PagingConfig(1)){
       CharacterPagingSource(charactersRepo)

   }.flow.cachedIn(viewModelScope)


    fun getAllCharactersFromRoom() = charactersRepo.getAllCharacters()

    fun upsertCharacter(ch:Character) =viewModelScope.launch {
        charactersRepo.upsetCharacter(ch)
    }



}


