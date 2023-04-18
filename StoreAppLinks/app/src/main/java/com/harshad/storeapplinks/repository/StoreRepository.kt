package com.harshad.storeapplinks.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.harshad.storeapplinks.repository.remote.ApiService
import com.harshad.storeapplinks.repository.remote.RetrofitHelper
import com.harshad.storeapplinks.repository.remote.model.SubCategory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoreRepository {

    val apiClient = RetrofitHelper.getInstance().create(ApiService::class.java)
    val subCatAppListLiveData = MutableLiveData<List<SubCategory>>()

    fun getSubCategoryApps(): LiveData<List<SubCategory>> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = apiClient.getSubCategoryApps()
            val listOfAppCenter = response.appCenter
            val listOfSubCategory = mutableListOf<SubCategory>()
            for (i in 0 until listOfAppCenter.size) {
                listOfSubCategory.addAll(listOfAppCenter[i].subCategory)
                subCatAppListLiveData.postValue(listOfSubCategory)
            }
        }
        return subCatAppListLiveData
    }

}