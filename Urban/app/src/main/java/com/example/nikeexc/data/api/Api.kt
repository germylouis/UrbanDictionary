package com.example.nikeexc.data.api

import com.example.nikeexc.domain.UrbanResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface Api {

    @GET("/define")
    fun getDefinition(
        @Header("x-rapidapi-key") apiKey: String,
        @Query("term") word: String
    ): Observable<UrbanResponse>
}