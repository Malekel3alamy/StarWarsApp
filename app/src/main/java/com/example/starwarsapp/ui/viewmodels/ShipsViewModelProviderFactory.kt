package com.example.starwarsapp.ui.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarsapp.repo.ShipsRepo

class ShipsViewModelProviderFactory(val app :Application , val shipsRepo :ShipsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShipsViewModel(shipsRepo) as T
    }
}