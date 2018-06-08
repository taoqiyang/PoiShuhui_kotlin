package com.flying.xiaopo.poishuhui_kotlin.domain.network

import com.flying.xiaopo.poishuhui_kotlin.domain.model.BookDetail
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Response
import com.google.gson.reflect.TypeToken
import okhttp3.Request


class BookDetailSource : Source<Response<BookDetail>, Int> {
  override fun fetch(param: Int): Response<BookDetail> {
      val url = Consts.BASE_URL + "cartoon/book_ish/id/$param.json"
      val response = HttpClient.newCall(Request.Builder().url(url).build()).execute()
      if (!response.isSuccessful) {
          return Response<BookDetail>(-1, "网络异常:" + response.code(), null)
      }
      return Util.gson.fromJson<Response<BookDetail>>(response.body()?.string(), object : TypeToken<Response<BookDetail>>() {}.type)
  }
}
