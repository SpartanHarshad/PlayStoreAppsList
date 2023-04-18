package com.harshad.storeapplinks.repository.remote.model

import com.google.gson.annotations.SerializedName


data class AppCenter (

    @SerializedName("id"           ) var id          : Int?                   = null,
    @SerializedName("position"     ) var position    : Int?                   = null,
    @SerializedName("name"         ) var name        : String?                = null,
    @SerializedName("is_active"    ) var isActive    : Int?                   = null,
    @SerializedName("catgeory"     ) var catgeory    : String?                = null,
    @SerializedName("sub_category" ) var subCategory : ArrayList<SubCategory> = arrayListOf()

)