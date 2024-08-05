package com.example.starwarsapp.api.models.characters

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "charactersTable"
)
@Parcelize
data class Character(
    @PrimaryKey
    val id :Int,
    val birth_year: String,
    val created: String,
    val edited: String,
    val eye_color: String,
    val films: List<String>,
    val gender: String,
    val hair_color: String,
    val height: String,
    val homeworld: String,
    val mass: String,
    var name: String,
    val skin_color: String,
    val species: List<String>,
    val starships: List<String>,
    val url: String,
     val vehicles: List<String>
) : Parcelable