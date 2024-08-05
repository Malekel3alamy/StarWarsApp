package com.example.starwarsapp.ui

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.starwarsapp.R
import com.example.starwarsapp.databinding.ActivityMainBinding
import com.example.starwarsapp.databinding.FragmentCharactersBinding
import com.example.starwarsapp.db.ChAndShDatabase
import com.example.starwarsapp.repo.CharactersRepo
import com.example.starwarsapp.repo.ShipsRepo
import com.example.starwarsapp.ui.viewmodels.CharactersViewModel
import com.example.starwarsapp.ui.viewmodels.CharactersViewModelProvideFactory
import com.example.starwarsapp.ui.viewmodels.ShipsViewModel
import com.example.starwarsapp.ui.viewmodels.ShipsViewModelProviderFactory
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    lateinit var  charactersViewModel : CharactersViewModel
    lateinit var shipsViewModel: ShipsViewModel
    lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instance Of CharactersViewModel
         val db:ChAndShDatabase = ChAndShDatabase(this)
        val characRepo = CharactersRepo(db)
        val shipsRepo = ShipsRepo(db)
        val charactersViewModelProvideFactory = CharactersViewModelProvideFactory(application,characRepo)
        charactersViewModel = ViewModelProvider(this,charactersViewModelProvideFactory)
            .get(CharactersViewModel::class.java)

        // Instance Of ShipsViewModel
        val shipsViewModelProvideFactory = ShipsViewModelProviderFactory(application,shipsRepo)
        shipsViewModel = ViewModelProvider(this,shipsViewModelProvideFactory)
            .get(ShipsViewModel::class.java)
        // Nav Controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        // TabLayout Listener
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener  {
            override fun onTabSelected(tab: TabLayout.Tab?) {
when(tab?.position){
        0 ->{
    navController.navigate(R.id.charactersFragment)
}
     1 ->{
         navController.navigate(R.id.shipsFragment)
     }

}
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })


    }
}