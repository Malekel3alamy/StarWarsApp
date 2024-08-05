package com.example.starwarsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarsapp.repo.CharactersRepo

class CharactersViewModelProvideFactory(val app:Application, val charactersRepo:CharactersRepo) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return CharactersViewModel(charactersRepo) as T
    }
}