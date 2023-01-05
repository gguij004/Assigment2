package com.example.assigment_2.model.remote

import com.example.assigment_2.model.ItunesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {

    @GET(BASE_ITUNES_ENDPOINT)
    fun getNextItunesPage(
        @Query(PARAM_TERM) bookTitle: String,
        @Query(PARAM_START_INDEX) pageIndex: Int
    ): Call<ItunesResponse>

}