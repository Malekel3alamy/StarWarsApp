package com.example.starwarsapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsapp.R
import com.example.starwarsapp.adapter.CharactersAdapter
import com.example.starwarsapp.adapter.ShipsAdapter
import com.example.starwarsapp.databinding.FragmentShipsBinding
import com.example.starwarsapp.ui.MainActivity
import com.example.starwarsapp.ui.viewmodels.ShipsViewModel
import com.example.starwarsapp.utils.Resources
import com.example.starwarsapp.utils.checkInternetConnection
import kotlinx.coroutines.launch


class ShipsFragment : Fragment(R.layout.fragment_ships) {
    lateinit var binding : FragmentShipsBinding
    lateinit var shipsViewModel: ShipsViewModel
    val myAdapter = ShipsAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShipsBinding.bind(view)

        shipsViewModel = (activity as MainActivity).shipsViewModel
        setUpRecycler()

        if(checkInternetConnection(requireContext())){
            lifecycleScope.launch {
                shipsViewModel.shipsList.collect{
                    myAdapter.submitData(it)
                }
            }
        }else{
            binding.shItemError.visibility = View.VISIBLE
            binding.button.setOnClickListener {
                if (checkInternetConnection((activity as MainActivity).applicationContext)){
                    binding.shItemError.visibility = View.INVISIBLE
                    lifecycleScope.launch {
                        shipsViewModel.shipsList.collect {
                            myAdapter.submitData(it)
                        }
                    }

                }else{
                    Toast.makeText((activity as MainActivity).applicationContext," Network Error " ,Toast.LENGTH_SHORT).show()
                }

            }
        }


        // Setting Listener for Click On Item
        myAdapter.onItemClickListener {

            val bundle = Bundle().apply {
                putParcelable("ship",it)
            }
            findNavController().navigate(R.id.action_shipsFragment_to_shipDetailsFragment,bundle)
        }


    }

// Setup Ships Recycler
    fun setUpRecycler(){
        binding.shipsRV.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }


    }
