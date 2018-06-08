package com.flying.xiaopo.poishuhui_kotlin.domain.network

import com.flying.xiaopo.poishuhui_kotlin.domain.model.PageDetail
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Response
import com.google.gson.reflect.TypeToken
import okhttp3.Request


class ComicSource : Source<Response<PageDetail>, String> {
    override fun fetch(param: String): Response<PageDetail> {
        val url = Consts.BASE_URL + "cartoon/post/num/$param.json"
        val response = HttpClient.newCall(Request.Builder().url(url).build()).execute()
        if (!response.isSuccessful) {
            return Response<PageDetail>(-1, "网络异常:" + response.code(), null)
        }
        return Util.gson.fromJson<Response<PageDetail>>(response.body()?.string(), object : TypeToken<Response<PageDetail>>() {}.type)
    }
}
