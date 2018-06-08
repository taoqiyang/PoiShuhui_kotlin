package com.flying.xiaopo.poishuhui_kotlin.ui.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.flying.xiaopo.poishuhui_kotlin.R
import com.flying.xiaopo.poishuhui_kotlin.ui.adapter.ContentPagerAdapter
import com.flying.xiaopo.poishuhui_kotlin.ui.fragment.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.startActivity
import java.util.*

class MainActivity : AppCompatActivity() {

  companion object {
    val GITHUB_URL = "https://github.com/wuapnjie/PoiShuhui-Kotlin"
  }

  val nameResList: ArrayList<Int> = arrayListOf(R.string.tab_one, R.string.tab_two, R.string.tab_three)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    init()
  }

  private fun init() {
    setSupportActionBar(toolbar)
    fab.setOnClickListener { jump2MyGithub() }
    val fragments = ArrayList<Fragment>()


    fragments.add(HomeFragment())
    fragments.add(HomeFragment())
    fragments.add(HomeFragment())
//    fragments.add(BookFragment())
//    fragments.add(NewsFragment())

    val nameList = nameResList.map(this::getString)

    viewPager.adapter = ContentPagerAdapter(fragments, nameList, supportFragmentManager)
    viewPager.offscreenPageLimit = 2

    tabLayout.setupWithViewPager(viewPager)

    //        Picasso.with(this).setIndicatorsEnabled(true)
  }

  private fun jump2MyGithub() {
//    val uri = Uri.parse(GITHUB_URL);
//    val intent = Intent(Intent.ACTION_VIEW, uri)
//    startActivity(intent)
    browse(GITHUB_URL)
  }


  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    val id = item.itemId

    if (id == R.id.action_about) {
//      val intent = Intent(this, AboutActivity().javaClass)
//      startActivity(intent)
      startActivity<AboutActivity>()
      return true
    }

    return super.onOptionsItemSelected(item)
  }
}
