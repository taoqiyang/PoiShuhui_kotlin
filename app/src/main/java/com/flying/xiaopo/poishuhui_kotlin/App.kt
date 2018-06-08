package com.flying.xiaopo.poishuhui_kotlin

import android.app.Application
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.tencent.smtt.sdk.QbSdk
import okhttp3.OkHttpClient

/**
 * @author wupanjie
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val client = OkHttpClient()
        val picasso = Picasso.Builder(this)
                .downloader(OkHttp3Downloader(client))
                .build()

        Picasso.setSingletonInstance(picasso)
        //x5内核初始化接口
        QbSdk.initX5Environment(applicationContext, object: QbSdk.PreInitCallback{
            override fun onViewInitFinished(p0: Boolean) {
                log("onViewInitFinished--" + p0)
            }

            override fun onCoreInitFinished() {
                log("onCoreInitFinished")
            }
        })
    }
}
