package com.example.starwarsapp.api.models.ships

data class ShipsResponse(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: MutableList<Ship>
)