package com.flying.xiaopo.poishuhui_kotlin.ui.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.Cover
import com.flying.xiaopo.poishuhui_kotlin.domain.network.Consts
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherBinder
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherViewHolder
import com.flying.xiaopo.poishuhui_kotlin.loadUrl
import kotlinx.android.synthetic.main.item_cover.view.*

/**
 * @author wupanjie
 */
class CoverBinder : AnotherBinder<Cover>() {
  override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
    val view = inflater.inflate(R.layout.item_cover, parent, false)
    return AnotherViewHolder(view)
  }

  override fun renderView(holder: AnotherViewHolder, itemView: View, item: Cover) {
    itemView.tvCover.text = item.name
    itemView.ivCover.loadUrl(Consts.BASE_URL + item.thumb)
  }
}