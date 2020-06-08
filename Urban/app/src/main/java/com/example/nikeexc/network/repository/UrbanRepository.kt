package com.example.nikeexc.network.repository

import com.example.nikeexc.domain.UrbanResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UrbanRepository {
    fun getDefinitionsList(word: String): Observable<UrbanResponse> {
        return RetrofitInstance.getSchools(word)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}