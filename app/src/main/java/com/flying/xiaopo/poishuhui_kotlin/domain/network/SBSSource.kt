/*
package com.flying.xiaopo.poishuhui_kotlin.domain.network

import com.flying.xiaopo.poishuhui_kotlin.getHtml
import org.jsoup.Jsoup

*/
/**
 * @author wupanjie
 *//*

class SBSSource : Source<String> {
  override fun fetch(page: Int): String {
    val html = getHtml(url)
    val doc = Jsoup.parse(html)

    //TODO Need To do better
    val contentHtml =
        "<html>${doc.select("head")}<body>${doc.select("div.mangaContentMainImg")}</body></html>"
    return contentHtml
  }

}*/
