package com.example.starwarsapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.starwarsapp.R
import com.example.starwarsapp.api.models.ships.Ship
import com.example.starwarsapp.databinding.FragmentShipDetailsBinding
import com.example.starwarsapp.ui.MainActivity
import com.example.starwarsapp.ui.viewmodels.ShipsViewModel


class ShipDetailsFragment : Fragment(R.layout.fragment_ship_details) {
    lateinit var binding: FragmentShipDetailsBinding
    lateinit var shipsViewModel : ShipsViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShipDetailsBinding.bind(view)

        shipsViewModel = (activity as MainActivity).shipsViewModel

        val ship = arguments?.getParcelable<Ship>("ship")

        binding.apply {
            crewTv.text = "Crew : ${ship?.crew}"
            nameTv.text = "Name : ${ship?.name}"
            modelTv.text= "Model : ${ship?.model}"
            manufaturerTv.text="Manufacturer : ${ship?.manufacturer}"
            passengersTv.text = " Passengers : ${ship?.passengers}"

        }

        binding.shFloatButton.setOnClickListener {
            if(ship!= null ){
                shipsViewModel.upsertShip(ship)
                Toast.makeText(this.context," Ship Added Successfully to Favourites ", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context," No Ship Provided ", Toast.LENGTH_SHORT).show()
            }

        }
    }

}