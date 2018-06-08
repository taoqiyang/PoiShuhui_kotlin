package com.flying.xiaopo.poishuhui_kotlin.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import com.flying.xiaopo.poishuhui_kotlin.domain.model.PageData
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Response
import com.flying.xiaopo.poishuhui_kotlin.domain.network.CoverSource
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherAdapter
import com.flying.xiaopo.poishuhui_kotlin.ui.activity.BookDetailActivity
import com.flying.xiaopo.poishuhui_kotlin.ui.binder.CoverBinder
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import java.util.*

class HomeFragment : Fragment() {

    val source: CoverSource = CoverSource()
    var page: Int = 1
    var totalPage: Int = 1
    var mData = ArrayList<Cover>()
    var loading: Boolean = false

    private lateinit var coverList: RecyclerView

    lateinit var homeRefresh: SwipeRefreshLayout

    lateinit var adapter: AnotherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    /**
     * init setting view
     */
    private fun initView(view: View) {
        homeRefresh = view.homeRefresh
        coverList = view.homeList

        val layoutManager = GridLayoutManager(context, 2)
        coverList.layoutManager = layoutManager

        adapter = AnotherAdapter()
                .with(Cover::class.java, CoverBinder().clickWith { item, _ ->
                    jump2Comic(item)
                })
        coverList.adapter = adapter
        coverList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                    if (layoutManager.childCount > 0             //当当前显示的item数量>0
                            && lastVisiblePosition >= layoutManager.itemCount - 1           //当当前屏幕最后一个加载项位置>=所有item的数量
                            && layoutManager.itemCount > layoutManager.childCount) { // 当当前总Item数大于可见Item数
                        loadMore()
                    }
                }
            }
        })

        homeRefresh.setOnRefreshListener {
            refresh()
        }
        homeRefresh.post {
            homeRefresh.isRefreshing = true
        }
    }

    private fun jump2Comic(cover: Cover) {
        activity.startActivity<BookDetailActivity>(BookDetailActivity.COVER to cover)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && mData.size == 0) {
            refresh()
        }
    }

    private fun refresh() {
        page = 1
        doAsync {
            val resp: Response<PageData<Cover>> = source.fetch(page)
            uiThread {
                homeRefresh.isRefreshing = false
                if (!resp.isSuccessful()) {
                    toast(resp.errMsg)
                } else {
                    val data = resp.data
                    if(data != null){
                        totalPage = resp.data.totalPages
                        mData.clear()
                        mData.addAll(resp.data.data)
                        adapter.update(mData)
                    }
                }
            }
        }
    }

    private fun loadMore() {
        if (loading || page == totalPage) {
            return
        }
        loading = true
        doAsync {
            val resp: Response<PageData<Cover>> = source.fetch(page + 1)
            uiThread {
                loading = false
                if (resp.errNo != 0) {
                    toast(resp.errMsg)
                } else {
                    val data = resp.data
                    if(data != null){
                        totalPage = data.totalPages
                        mData.addAll(data.data)
                        adapter.append(data.data)
                        page = data.currentPage
                    }
                }
            }
        }
    }

}