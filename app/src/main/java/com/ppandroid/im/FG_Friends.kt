package com.ppandroid.im

import com.hyphenate.chat.EMClient
import com.hyphenate.easeui.EaseConstant
import com.hyphenate.easeui.domain.EaseUser
import com.hyphenate.easeui.ui.EaseChatFragment
import com.hyphenate.easeui.ui.EaseContactListFragment
import com.hyphenate.exceptions.HyphenateException
import com.ppandroid.im.base.AC_ContentFG
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask


/**
 * Created by yeqinfu on 2017/7/28.
 */
class FG_Friends : EaseContactListFragment() {
    override fun onResume() {
        val callable = Callable<MutableMap<String, EaseUser>?> {
            getContacts()
        }
        val future = FutureTask(callable)
        Thread(future).start()
        setContactsMap(future.get())

        super.onResume()
    }

    override fun setUpView() {
        setContactListItemClickListener { user ->
            var it = AC_ContentFG.createIntent(activity, EaseChatFragment::class.java.name)
            it.putExtra(EaseConstant.EXTRA_USER_ID, user.username)
            startActivity(it)
        }
        super.setUpView()
    }

    private fun getContacts(): MutableMap<String, EaseUser>? {
        val map = HashMap<String, EaseUser>()
        try {
            val userNames = EMClient.getInstance().contactManager().allContactsFromServer
            for (userId in userNames) {
                var item = EaseUser(userId)
                item.nick = "假的昵称"
                map.put(userId, item)
            }
        } catch (e: HyphenateException) {
            e.printStackTrace()
        }

        return map
    }


}