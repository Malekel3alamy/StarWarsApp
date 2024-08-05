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
import com.example.starwarsapp.api.models.ships.Ship

class ShipsAdapter:PagingDataAdapter<Ship,ShipsAdapter.MyViewHolder>(differCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipsAdapter.MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.recycler_item,parent,false))
    }

    override fun onBindViewHolder(holder: ShipsAdapter.MyViewHolder, position: Int) {
        val ship = getItem(position)
        Log.d("CharactersAdapter","${ship?.name}")
        holder.chracterName.text = ship?.name

        holder.itemView.setOnClickListener {
            onItemClickListener?.let {
                ship.let {ship ->
                    it(ship!!)

                }
            }
        }
    }


    class MyViewHolder( view: View) : ViewHolder(view) {

        val chracterName: TextView = view.findViewById(R.id.characterName)

    }
companion object{
    private val differCallback = object  : DiffUtil.ItemCallback<Ship>(){
        override fun areItemsTheSame(oldItem: Ship, newItem: Ship): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Ship, newItem: Ship): Boolean {

            return  oldItem == newItem
        }

    }
}



    // Listener
    private var onItemClickListener :((Ship) -> Unit)? = null
    fun onItemClickListener(listener : (Ship) -> Unit){
        onItemClickListener =listener
    }
}