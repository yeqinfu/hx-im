package com.ppandroid.im

import com.hyphenate.easeui.EaseConstant
import com.hyphenate.easeui.ui.EaseChatFragment
import com.hyphenate.easeui.ui.EaseConversationListFragment
import com.ppandroid.im.base.AC_ContentFG


/**
 * Created by yeqinfu on 2017/7/28.
 */
class FG_RecentContacts: EaseConversationListFragment() {
    override fun setUpView() {
        setConversationListItemClickListener { conversation ->
            var it = AC_ContentFG.createIntent(activity, EaseChatFragment::class.java.name)
            it.putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId())
            startActivity(it) }
        super.setUpView()
    }




}