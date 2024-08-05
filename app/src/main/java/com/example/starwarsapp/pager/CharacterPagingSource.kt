package com.example.starwarsapp.pager

import android.graphics.pdf.PdfRenderer.Page
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsapp.api.models.characters.Character
import com.example.starwarsapp.api.models.characters.CharactersResponse
import com.example.starwarsapp.repo.CharactersRepo

class CharacterPagingSource(private val charactersRepo: CharactersRepo) : PagingSource<Int,Character>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
       return try {
            val position = params.key ?: 1
            val response = charactersRepo.getCharacters(position)

            if (response.isSuccessful) {
                val currentPage = params.key ?:1
                val response = charactersRepo.getCharacters(currentPage)
                val data = response.body()!!.results
                val responseData = mutableListOf<Character>()
                responseData.addAll(data)
                LoadResult.Page(
                    data = responseData ,
                    prevKey = if (currentPage == 1) null else -1,
                    nextKey = currentPage.plus(1)
                )
            } else {
                LoadResult.Error(throw Exception("No Response"))
            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {

        return null
    }
}