package com.flying.xiaopo.poishuhui_kotlin.ui.binder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.domain.model.News
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherBinder
import com.flying.xiaopo.poishuhui_kotlin.kits.recycler.AnotherViewHolder
import kotlinx.android.synthetic.main.item_news.view.*

/**
 * @author wupanjie
 */
class NewsBinder : AnotherBinder<News>() {
  override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup): AnotherViewHolder {
    val view = inflater.inflate(R.layout.item_news, parent, false)
    return AnotherViewHolder(view)
  }

  override fun renderView(holder: AnotherViewHolder, itemView: View, item: News) {
    itemView.tvTitle.text = item.title
    itemView.tvTime.text = item.createdTime
    if (holder.adapterPosition % 2 == 0) {
      itemView.container.setBackgroundResource(R.color.alpha_grey)
    }else{
      itemView.container.setBackgroundResource(R.color.material_white)
    }

    itemView.container.setOnClickListener {
//      WebDetailDialog(itemView.context, item, NewsDetailSource())
    }
  }

}