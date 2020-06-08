package com.example.nikeexc.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nikeexc.network.repository.UrbanRepository


class VMFactory(private val urbanRepository: UrbanRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UrbanViewModel(urbanRepository) as T
    }

}