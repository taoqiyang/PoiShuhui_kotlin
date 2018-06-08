package com.flying.xiaopo.poishuhui_kotlin.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Page
import com.flying.xiaopo.poishuhui_kotlin.domain.network.BookDetailSource
import com.flying.xiaopo.poishuhui_kotlin.domain.network.ComicSource
import com.flying.xiaopo.poishuhui_kotlin.domain.network.Consts
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherAdapter
import com.flying.xiaopo.poishuhui_kotlin.ui.binder.PageBinder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import org.jetbrains.anko.*


class BookDetailActivity : AppCompatActivity() {
    companion object {
        val COVER = "cover"
    }

    private lateinit var adapter: AnotherAdapter
    private lateinit var cover: Cover

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(toolbar)
        if (!intent.hasExtra(COVER)) {
            toast("别逗")
            finish()
            return
        }
        cover = intent.getParcelableExtra(COVER)
        init()
    }

    private fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar.title = cover.name
        Picasso.with(this).load(Consts.BASE_URL + cover.thumb).into(backgroundImage)

        pageRefresh.setOnRefreshListener { load() }
        pageList.layoutManager = GridLayoutManager(this, 4)

        adapter = AnotherAdapter().with(Page::class.java, PageBinder().clickWith { _, position ->
            jump2Read(position)
        })

        pageList.adapter = adapter
        pageList.post {
            pageRefresh.isRefreshing = true
            load()
        }
    }


    private fun load() = doAsync {
        val response = BookDetailSource().fetch(cover.book)
        uiThread {
            pageRefresh.isRefreshing = false
            if (response.isFaild()) {
                toast(response.errMsg)
            } else {
//                val banner = response.data?.book?.banner
//                if(banner != null && !banner.isEmpty()){
//                    backgroundImage.loadUrl(Consts.BASE_URL + banner)
//                }
                val posts = response.data?.cartoon?.info?.posts
                if (posts != null && posts.isNotEmpty()) {
                    val pages: ArrayList<Page> = ArrayList()
                    val bookID = response.data.book.id
                    for ((key, list) in posts) {
                        list[0].num = key.substringAfter("-", "").toInt()
                        list[0].pageKey = "$bookID-0-$key"
                        pages.add(list[0])
                    }
                    adapter.update(pages)
                }
            }
        }
    }

    private fun jump2Read(position: Int) {
        val page = adapter.items[position] as? Page ?: return
        val progressDialog = indeterminateProgressDialog(message = "Please wait a bit…", title = "Loading", init = {
            setCancelable(false)
        })
        doAsync {
            val response = ComicSource().fetch(page.pageKey)
            uiThread {
                progressDialog.dismiss()
                if (response.isFaild()) {
                    toast(response.errMsg)
                } else {
                    response.data?.comics?.get(0)?.url.let { url ->
                        startActivity<ComicActivity>(ComicActivity.COMIC_URL to url)
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_book_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == android.R.id.home) {
            onBackPressed()
            return true
        } else if (id == R.id.action_info) {
            showBookInfo()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showBookInfo() {
//        val bookInfo = bookDetail.info
//        pageList.snackbar(bookInfo.description + "\n" + bookInfo.updateTime)
    }
}
