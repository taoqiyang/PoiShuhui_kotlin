package com.flying.xiaopo.poishuhui_kotlin.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.view.View

import com.flying.xiaopo.poishuhui_kotlin.BuildConfig
import com.tencent.smtt.sdk.QbSdk
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebSettings.LayoutAlgorithm
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

class X5WebView
constructor(arg0: Context, arg1: AttributeSet) : WebView(arg0, arg1) {

    init {
        val client = object : WebViewClient() {
            /**
             * 防止加载网页时调起系统浏览器
             */
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view!!.loadUrl(url)
                return true
            }
        }
        this.webViewClient = client
        initWebViewSettings()
        this.view.isClickable = true
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebViewSettings() {
        val webSetting = this.settings
        webSetting.javaScriptEnabled = true
        webSetting.javaScriptCanOpenWindowsAutomatically = true
        webSetting.allowFileAccess = true
        webSetting.layoutAlgorithm = LayoutAlgorithm.NARROW_COLUMNS
        webSetting.setSupportZoom(true)
        webSetting.builtInZoomControls = true
        webSetting.useWideViewPort = true
        webSetting.setSupportMultipleWindows(false)
        webSetting.setAppCacheEnabled(true)
        webSetting.domStorageEnabled = true
        webSetting.setGeolocationEnabled(true)
        webSetting.setAppCacheMaxSize(java.lang.Long.MAX_VALUE)
        webSetting.setAppCachePath(context.getDir("x5cache", 0).path)
        webSetting.databasePath = context.getDir("x5databases", 0).path
        webSetting.setGeolocationDatabasePath(context.getDir("x5geolocation", 0).path)
        webSetting.pluginState = WebSettings.PluginState.ON_DEMAND
        //        webSetting.setCacheMode(WebSettings.LOAD_NO_CACHE);
    }

    override fun drawChild(canvas: Canvas, child: View, drawingTime: Long): Boolean {
        val ret = super.drawChild(canvas, child, drawingTime)
        if (!BuildConfig.DEBUG) {
            return ret
        }
        canvas.save()
        val paint = Paint()
        paint.color = 0x7fff0000
        paint.textSize = 24f
        paint.isAntiAlias = true
        if (x5WebViewExtension != null) {
            canvas.drawText(this.context.packageName + "-pid:"
                    + android.os.Process.myPid(), 10f, 50f, paint)
            canvas.drawText(
                    "X5  Core:" + QbSdk.getTbsVersion(this.context), 10f,
                    100f, paint)
        } else {
            canvas.drawText(this.context.packageName + "-pid:"
                    + android.os.Process.myPid(), 10f, 50f, paint)
            canvas.drawText("Sys Core", 10f, 100f, paint)
        }
        canvas.drawText(Build.MANUFACTURER, 10f, 150f, paint)
        canvas.drawText(Build.MODEL, 10f, 200f, paint)
        canvas.restore()
        return ret
    }
}
