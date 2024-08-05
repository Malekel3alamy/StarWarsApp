package com.example.starwarsapp.api.models.ships

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "shipsTable"
)
@Parcelize
data class Ship(
    @PrimaryKey
    val id :Int,
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val cost_in_credits: String,
    val created: String,
    val crew: String,
    val edited: String,
    val films: List<String>,
    val hyperdrive_rating: String,
    val length: String,
    val manufacturer: String,
    val max_atmosphering_speed: String,
    val model: String,
    val name: String,
    val passengers: String,
    val pilots: List<String>,
    val starship_class: String,
    val url: String
) : Parcelable