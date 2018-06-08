package com.flying.xiaopo.poishuhui_kotlin.ui.binder

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Page
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherBinder
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherViewHolder
import kotlinx.android.synthetic.main.item_page.view.*

/**
 * @author wupanjie
 */
class PageBinder : AnotherBinder<Page>() {
    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
        val itemView = inflater.inflate(R.layout.item_page, parent, false)
        return AnotherViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun renderView(holder: AnotherViewHolder, itemView: View, item: Page) {
        itemView.tvPage.text = "第${item.num}话"
    }
}