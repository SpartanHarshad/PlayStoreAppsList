package com.harshad.storeapplinks.repository.remote

import com.harshad.storeapplinks.repository.remote.model.AppResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/com.hd.camera.apps.high.quality")
    suspend fun getSubCategoryApps(): AppResponse

}