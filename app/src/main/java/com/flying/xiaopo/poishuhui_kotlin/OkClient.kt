package com.flying.xiaopo.poishuhui_kotlin

import okhttp3.OkHttpClient

/**
 * 单例OkHttpClient
 * Created by Flying SnowBean on 16-3-6.
 */
object OkClient {
  val instance = OkHttpClient()
}
