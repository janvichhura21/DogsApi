package com.example.dogsapi.model.network

import com.example.dogsapi.model.Dogs
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val BASE_URL="https://api.thedogapi.com"
    }
    @GET("v1/images/search")
    suspend fun getDogs(
        @Query("page")page:Int,
        @Query("limit")limit:Int
    ):List<Dogs>
}