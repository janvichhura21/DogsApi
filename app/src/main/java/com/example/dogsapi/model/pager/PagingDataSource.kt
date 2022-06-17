package com.example.dogsapi.model.pager

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dogsapi.model.Dogs
import com.example.dogsapi.model.network.ApiService
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PagingDataSource @Inject constructor(val apiService: ApiService):PagingSource<Int,Dogs>() {

    override suspend fun load(params: LoadParams<Int>): PagingSource.LoadResult<Int, Dogs> {
       val pages=params.key?:1
        return try {
            val response=apiService.getDogs(pages,params.loadSize)
            LoadResult.Page(
                response,
                prevKey = if (pages==1)null else pages-1,
                nextKey = if (response.isEmpty())null else  pages+1
            )
        }catch (e:IOException){
            LoadResult.Error(e)
        }catch (e:HttpException){
            LoadResult.Error(e)
        }
    }
    override fun getRefreshKey(state: PagingState<Int, Dogs>): Int? {
        return null
    }
}