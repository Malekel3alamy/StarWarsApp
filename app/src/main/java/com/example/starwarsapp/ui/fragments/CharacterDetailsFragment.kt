package com.example.starwarsapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.starwarsapp.R
import com.example.starwarsapp.api.models.characters.Character
import com.example.starwarsapp.databinding.FragmentCharacterDeatailsBinding
import com.example.starwarsapp.ui.MainActivity
import com.example.starwarsapp.ui.viewmodels.CharactersViewModel


class CharacterDetailsFragment : Fragment(R.layout.fragment_character_deatails) {
    lateinit var charatersViewModel: CharactersViewModel
    lateinit var binding : FragmentCharacterDeatailsBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCharacterDeatailsBinding.bind(view)

        charatersViewModel = (activity as MainActivity).charactersViewModel

        val character = arguments?.getParcelable<Character>("character")

        binding.apply {
            chNameTv.text=" Name : ${character?.name}"
            chEyeColorTv.text=" EyeColor : ${character?.eye_color}"
            chBirthYearTv.text=" BirthYear : ${character?.birth_year}"
            chHairColorTv.text=" HairColor : ${character?.hair_color}"
            chMassTv.text=" HairColor : ${character?.mass}"
            chGenderTv.text=" Gender : ${character?.gender}"
            chHeightTv.text=" Height : ${character?.height}"

        }

      binding.chFloatingButton.setOnClickListener {
          if(character!= null && character.films != null &&character.species!= null && character.starships!= null&&character.vehicles != null){
              charatersViewModel.upsertCharacter(character)
              Toast.makeText(this.context," Character Added Successfully to Favourites ",Toast.LENGTH_SHORT).show()
          }else{
              Toast.makeText(context," No Character Provided ",Toast.LENGTH_SHORT).show()
          }

      }

    }
}