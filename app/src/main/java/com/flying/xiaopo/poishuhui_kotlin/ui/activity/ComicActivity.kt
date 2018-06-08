package com.flying.xiaopo.poishuhui_kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.toast
import kotlinx.android.synthetic.main.activity_comic_web.*


class ComicActivity : AppCompatActivity() {
    companion object {
        val COMIC_URL = "url"
    }

    lateinit var pageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_web)
        if (!intent.hasExtra(COMIC_URL)) {
            toast("别逗")
            finish()
            return
        }
        pageUrl = intent.getStringExtra(COMIC_URL)

        webView.setInitialScale(70)
        webView.post({
            webView.loadUrl(pageUrl)
        })
    }
}
