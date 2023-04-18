package com.harshad.storeapplinks.repository.remote.model

import com.google.gson.annotations.SerializedName


data class AppResponse (

    @SerializedName("status"            ) var status          : Int?                 = null,
    @SerializedName("message"           ) var message         : String?              = null,
    @SerializedName("data"              ) var data            : ArrayList<Data>      = arrayListOf(),
    @SerializedName("app_center"        ) var appCenter       : ArrayList<AppCenter> = arrayListOf(),
    @SerializedName("home"              ) var home            : ArrayList<Home>      = arrayListOf(),
    @SerializedName("more_apps"         ) var moreApps        : ArrayList<MoreApps>  = arrayListOf(),
    @SerializedName("native_add"        ) var nativeAdd       : NativeAdd?           = NativeAdd(),
    @SerializedName("translator_ads_id" ) var translatorAdsId : TranslatorAdsId?     = TranslatorAdsId()

)