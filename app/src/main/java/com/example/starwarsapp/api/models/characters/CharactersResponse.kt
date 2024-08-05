package com.example.starwarsapp.api.models.characters

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: MutableList<Character>
)