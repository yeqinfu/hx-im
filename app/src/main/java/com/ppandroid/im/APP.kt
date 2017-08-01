package com.ppandroid.im

import android.app.Application
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions
import com.hyphenate.easeui.controller.EaseUI
import com.ppandroid.im.bean.ET_APP
import com.ppandroid.im.utils.DebugLog
import com.ppandroid.im.utils.Utils_UserInfo
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by yeqinfu on 2017/8/1.
 */

class APP : Application() {
    override fun onCreate() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
        super.onCreate()
        var options =EMOptions()
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.acceptInvitationAlways=true
        EaseUI.getInstance().init(applicationContext, options)
    }

    /**
     * 环信登录
     */
    fun hxLogin(){
        var body=Utils_UserInfo.getUserInfo(this)
        body.resultData?.let {
            EMClient.getInstance().login(it.hxid, it.hxpassword, object : EMCallBack {
                //回调
                override fun onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups()
                    EMClient.getInstance().chatManager().loadAllConversations()
                    DebugLog.d("main", "登录聊天服务器成功！")
                }

                override fun onProgress(progress: Int, status: String) {

                }

                override fun onError(code: Int, message: String) {
                    DebugLog.d("main", "登录聊天服务器失败！")
                }
            })
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ET_APP) {
        if (event.taskId === ET_APP.TASKID_HX_LOGIN) {
            hxLogin()
        }
    }

}
