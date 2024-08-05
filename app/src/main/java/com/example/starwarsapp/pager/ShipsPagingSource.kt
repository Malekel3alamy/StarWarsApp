package com.example.starwarsapp.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.starwarsapp.api.models.ships.Ship
import com.example.starwarsapp.repo.ShipsRepo
import retrofit2.HttpException

class ShipsPagingSource(private val shipsRepo: ShipsRepo) : PagingSource<Int, Ship>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Ship> {

        return try{
             val currentPage = params.key ?:1
            val response = shipsRepo.getShips(currentPage)
            val data = response.body()!!.results
            val responseData = mutableListOf<Ship>()
            responseData.addAll(data)
            LoadResult.Page(
                data = responseData,
                prevKey = if(currentPage == 1) null else -1,
                nextKey = currentPage.plus(1)
            )
        }catch (e : Exception){
            LoadResult.Error(e)
        }
        catch (httpE : HttpException){
            LoadResult.Error(httpE)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Ship>): Int? {
        return null
    }

}