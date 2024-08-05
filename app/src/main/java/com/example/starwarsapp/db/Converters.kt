package com.example.starwarsapp.db

import androidx.room.TypeConverter


class Converters {
    @TypeConverter
    fun fromList(list : List<String>) : String{
        if(list.size != 0)
        return list[0]
        else
            return ""
    }

    @TypeConverter
    fun  toList(item : String) : List<String>{
        return listOf(item)
    }
}