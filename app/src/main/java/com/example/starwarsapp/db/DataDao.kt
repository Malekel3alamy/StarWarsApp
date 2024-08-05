package com.example.starwarsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsapp.api.models.characters.Character
import com.example.starwarsapp.api.models.ships.Ship

@Dao
interface DataDao {

    @Query("SELECT * FROM charactersTable")
    fun getAllCharacters() : LiveData<List<Character>>


    @Query("SELECT * FROM shipsTable")
    fun getAllShips() : LiveData<List<Ship>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertCharacter(ch:Character)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertShip(sh:Ship)

    @Query("DELETE FROM charactersTable")
    suspend fun deleteAllCharacters()

    @Query("DELETE FROM shipsTable")
    suspend fun deleteAllShips()

}