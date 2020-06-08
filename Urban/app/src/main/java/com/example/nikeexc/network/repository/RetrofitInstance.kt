package com.example.nikeexc.network.repository

import com.example.nikeexc.data.api.Api
import com.example.nikeexc.domain.UrbanResponse
import com.example.nikeexc.util.Constants
import com.example.nikeexc.util.Constants.BASE_URL
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var api: Api

    init {
        api = createService(getInstance())
    }

    private fun getInstance(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createService(retrofit: Retrofit): Api {
        return retrofit.create<Api>(Api::class.java)
    }

    public fun getSchools(word: String): Observable<UrbanResponse> {
        return api.getDefinition(Constants.API_KEY, word)
    }
}