package com.harshad.storeapplinks.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.harshad.storeapplinks.repository.StoreRepository
import com.harshad.storeapplinks.repository.remote.model.SubCategory

class StoreViewModel(val storeRepository: StoreRepository) : ViewModel() {

    fun getSubAppsList(): LiveData<List<SubCategory>> {
        return storeRepository.getSubCategoryApps()
    }

}