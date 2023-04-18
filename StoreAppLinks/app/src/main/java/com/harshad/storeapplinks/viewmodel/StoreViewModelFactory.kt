package com.harshad.storeapplinks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.harshad.storeapplinks.repository.StoreRepository

class StoreViewModelFactory(val storeRepository: StoreRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StoreViewModel(storeRepository) as T
    }
}