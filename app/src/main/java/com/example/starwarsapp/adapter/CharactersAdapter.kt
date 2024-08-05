package com.example.starwarsapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.starwarsapp.R
import com.example.starwarsapp.api.models.characters.Character

class CharactersAdapter : PagingDataAdapter<Character,CharactersAdapter.MyViewHolder>(chDifferCallback) {
    object chDifferCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {

            return  oldItem == newItem
        }
    }


    class MyViewHolder( view: View) : ViewHolder(view) {

        val chracterName: TextView = view.findViewById(R.id.characterName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false))
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = getItem(position)
        Log.d("CharactersAdapter","${character?.name}")
        holder.chracterName.text = character?.name
        holder.setIsRecyclable(false)

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                character.let {character ->
                    it(character!!)

                }
            }
        }
    }




    // Listener
 private var onItemClickListener :((Character) -> Unit)? = null
    fun onItemClickListener(listener : (Character) -> Unit){
        onItemClickListener =listener
    }

}