package com.flying.xiaopo.poishuhui_kotlin.domain.network

import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import com.flying.xiaopo.poishuhui_kotlin.domain.model.PageData
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Response
import com.google.gson.reflect.TypeToken
import okhttp3.Request

class CoverPageData(count: Int, totalPages: Int, numsPerPage: Int, currentPage: Int, data: List<Cover>) : PageData<Cover>(count, totalPages, numsPerPage, currentPage, data)

class CoverSource : Source<Response<CoverPageData>, Int> {
    override fun fetch(param: Int): Response<CoverPageData> {
        val url = Consts.BASE_URL + "/cartoon/category_latest/page/$param.json"
        val response = HttpClient.newCall(Request.Builder().url(url).build()).execute()
        if (!response.isSuccessful) {
            return Response<CoverPageData>(-1, "网络异常:" + response.code(), null)
        }
        return Util.gson.fromJson<Response<CoverPageData>>(response.body()?.string(), object : TypeToken<Response<CoverPageData>>() {}.type)
    }

}