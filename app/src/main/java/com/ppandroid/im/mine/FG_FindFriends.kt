package com.ppandroid.im.mine

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hyphenate.chat.EMClient
import com.ppandroid.im.R
import com.ppandroid.im.base.FG_Base
import com.ppandroid.im.bean.BN_Base
import com.ppandroid.im.bean.mine.BN_FindFriends
import com.ppandroid.im.utils.TextUtils
import com.zhy.http.okhttp.MyCallBack
import com.zhy.http.okhttp.OKUtils
import kotlinx.android.synthetic.main.fg_find_friends.*
import org.jetbrains.anko.find

/**
 * Created by yeqinfu on 2017/8/1.
 */
class FG_FindFriends:FG_Base(){
    override fun fgRes(): Int= R.layout.fg_find_friends

    override fun afterViews() {
        btn_search.setOnClickListener {
            var key=et_input.text.toString()
            search(key)
        }
    }


    private fun search(key: String) {
        if (TextUtils.isEmpty(key)){
            toast(getString(R.string.msg_empty_input))
            return
        }

        var url="hxCtrl/findFriends?keyName="+key
        OKUtils.get(activity,url,BN_FindFriends::class.java,object:MyCallBack<BN_FindFriends>{
            override fun onResponse(response: BN_FindFriends?) {
                response?.resultData?.let {
                    var adapter= AD_Friends(activity, it)
                    lv_list.adapter=adapter

                }

            }

            override fun onError(error: BN_Base?) {
                error?.resultMessage?.let {
                    toast(it)
                }
            }

        })
    }

    class ViewHolder{
        var tv_name:TextView?=null
    }



    class AD_Friends(context: Activity, resultData: List<BN_FindFriends.ResultDataBean>):BaseAdapter(){
        internal var resultData: List<BN_FindFriends.ResultDataBean>?=null
        internal var context: Activity?=null
        init {
            this.context=context
            this.resultData=resultData
        }

        override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
            var layout:View?=null
            var holder: ViewHolder?=null
            if (p1!=null){
                layout=p1
                holder= layout.getTag() as ViewHolder?
            }else{
                layout=context?.layoutInflater?.inflate(R.layout.item_find_friends,null)
                holder= ViewHolder()
                holder.tv_name=layout?.find(R.id.tv_name)
            }
            holder?.let {
                it.tv_name?.text=resultData?.get(p0)?.user_name
            }
            layout?.find<View>(R.id.btn_add)?.setOnClickListener {
                //参数为要添加的好友的username和添加理由
                EMClient.getInstance().contactManager().addContact(resultData?.get(p0)?.hxid, "")
            }

            return layout
        }

        override fun getItem(p0: Int): Any {
            return resultData?.size?:0
        }

        override fun getItemId(p0: Int): Long {
            return p0.toLong()
        }

        override fun getCount(): Int {
            return resultData?.size?:0
        }

    }


}