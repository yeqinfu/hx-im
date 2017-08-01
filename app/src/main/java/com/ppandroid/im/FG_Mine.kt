package com.ppandroid.im

import android.content.Intent
import com.hyphenate.chat.EMClient
import com.ppandroid.im.base.AC_ContentFG
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.mine.FG_FindFriends
import com.ppandroid.im.utils.Utils_UserInfo
import kotlinx.android.synthetic.main.fg_mine.*

/**
 * Created by yeqinfu on 2017/7/28.
 */
class FG_Mine: FG_Base() {
    override fun fgRes(): Int=R.layout.fg_mine
    override fun afterViews() {
        var body=Utils_UserInfo.getUserInfo(activity)
        tv_name.text=body.resultData?.user_name?:""
        btn_logout.setOnClickListener {
            EMClient.getInstance().logout(true)
            Utils_UserInfo.clearUserInfo(activity)
            var it=Intent()
            it.setClass(activity,AC_Login::class.java)
            startActivity(it)
        }
        btn_search.setOnClickListener {
            var it=AC_ContentFG.createIntent(activity, FG_FindFriends::class.java.name)
            startActivity(it)
        }
    }

}