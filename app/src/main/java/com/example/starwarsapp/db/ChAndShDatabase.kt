package com.example.starwarsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.starwarsapp.api.models.characters.Character
import com.example.starwarsapp.api.models.ships.Ship
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(
    entities = [Character::class,Ship::class],
    version = 1
)
@TypeConverters(Converters::class)

abstract class ChAndShDatabase : RoomDatabase()  {

    abstract   fun getDao(): DataDao


    companion object{
        @Volatile
        private var instance : ChAndShDatabase? = null

        operator fun invoke (context: Context) :ChAndShDatabase{

            return Room.databaseBuilder(context.applicationContext,ChAndShDatabase::class.java,
                "ChAndShDatabase.db").build()
        }

         }
    }

