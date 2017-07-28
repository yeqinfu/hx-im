package com.ppandroid.im

import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.ppandroid.im.utils.Utils_Animator

class MainActivity : AppCompatActivity() {
    var mTabHost: FragmentTabHost?=null
    internal var layout0: View?=null
    internal var layout1: View?=null
    internal var layout2: View?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mTabHost = findViewById(android.R.id.tabhost) as FragmentTabHost
        //布局转成控件   依次是最近聊天  通信录  我的
        layout0 = layoutInflater.inflate(R.layout.tab_recent_contacts, null)
        layout1 = layoutInflater.inflate(R.layout.tab_friends, null)
        layout2 = layoutInflater.inflate(R.layout.tab_mine, null)
        mTabHost?.let {
            it.setup(this, supportFragmentManager, R.id.realtabcontent)
            it.addTab(it.newTabSpec("tab_recent_contacts").setIndicator(layout0), FG_RecentContacts::class.java, null)
            it.addTab(it.newTabSpec("tab_friends").setIndicator(layout1), FG_Friends::class.java, null)
            it.addTab(it.newTabSpec("tab_mine").setIndicator(layout2), FG_Mine::class.java, null)
            it.setOnTabChangedListener { p0 ->
                if (p0 == "tab_recent_contacts") {
                    Utils_Animator.scale(layout0, 300L, 1f, 1.2f, 1f, 0.8f, 1f)
                } else if (p0 == "tab_friends") {
                    Utils_Animator.scale(layout1, 300L, 1f, 1.2f, 1f, 0.8f, 1f)
                } else if (p0 == "tab_mine") {
                    Utils_Animator.scale(layout2, 300L, 1f, 1.2f, 1f, 0.8f, 1f)
                }
            }
        }

    }
}
