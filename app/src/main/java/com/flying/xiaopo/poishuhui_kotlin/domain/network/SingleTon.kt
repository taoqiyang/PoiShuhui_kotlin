package com.flying.xiaopo.poishuhui_kotlin.domain.network

import com.google.gson.Gson
import okhttp3.OkHttpClient

object HttpClient: OkHttpClient()

object Util: Any(){
    val gson: Gson = Gson()
}