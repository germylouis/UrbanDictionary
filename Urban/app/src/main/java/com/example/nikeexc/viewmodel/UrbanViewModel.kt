package com.example.nikeexc.viewmodel

import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nikeexc.adapter.UrbanAdapter
import com.example.nikeexc.domain.Definition
import com.example.nikeexc.network.repository.UrbanRepository
import com.jakewharton.rxbinding.support.v7.widget.RxSearchView
import io.reactivex.disposables.CompositeDisposable
import rx.Notification
import java.util.concurrent.TimeUnit

class UrbanViewModel constructor(private val repository: UrbanRepository) : ViewModel() {

    var upDownBoolean = true

    private val compositeDisposable = CompositeDisposable()
    var definitions: MutableList<Definition> = mutableListOf()

    val mAdapter = UrbanAdapter()

    private val errorMutableLiveData = MutableLiveData<Boolean>()
    val errorLiveData: LiveData<Boolean>
        get() = errorMutableLiveData


    private fun getWords(word: String) {

        repository
            .getDefinitionsList(word)
            .subscribe({ response ->

                for (item in response.list) {
                    item.definition = item.definition.replace("[", "")
                    item.definition = item.definition.replace("]", "")
                }

                mAdapter.definitionList = response.list
                definitions = response.list
                mAdapter.notifyDataSetChanged()
            }, {
                errorMutableLiveData.value = true
                Log.d("TAG", "Error: ${it.message}")
            })?.let {
                compositeDisposable.add(it)
            }
    }

    fun sortDefinitions(bool: Boolean) {
        upDownBoolean = bool
        if (upDownBoolean) {
            definitions.sortByDescending {
                it.thumbs_up
            }
            mAdapter.definitionList = definitions
            mAdapter.notifyDataSetChanged()
        } else {
            definitions.sortByDescending {
                it.thumbs_down
            }
            mAdapter.definitionList = definitions
            mAdapter.notifyDataSetChanged()
        }
        upDownBoolean = !upDownBoolean
    }

    fun searchDefinitions(searchView: SearchView) {
        RxSearchView.queryTextChanges(searchView)
            .doOnEach { notification: Notification<in CharSequence?> ->
                val query = notification.value as CharSequence?
                if (query != null && query.length > 2) {
                    getWords(query.toString())
                }
            }
            .debounce(300, TimeUnit.MILLISECONDS)
            .retry(3)
            .subscribe()
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}