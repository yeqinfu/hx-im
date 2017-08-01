package com.ppandroid.im.utils

import android.content.Context

import com.ppandroid.im.bean.login.BN_Login

/**
 * Created by yeqinfu on 2017/8/1.
 */

object Utils_UserInfo {
    val USER_NAME = "user_name"
    val TOKEN = "TOKEN"
    val HXID = "HXID"
    val HXPASSWORD = "HXPASSWORD"
    fun saveInfo(context: Context, bn: BN_Login) {
        val sp = Utils_SharedPreferences(context)
        sp.apply {
            bn.resultData?.let {
                put(USER_NAME, it.user_name)
                put(TOKEN, it.token)
                put(HXID, it.hxid)
                put(HXPASSWORD, it.hxpassword)
            }
        }

    }

    fun clearUserInfo(context: Context) {
        val sp = Utils_SharedPreferences(context)
        sp.apply {
            put(USER_NAME, "")
            put(TOKEN, "")
            put(HXID, "")
            put(HXPASSWORD, "")
        }
    }

    fun getUserInfo(context: Context): BN_Login {
        val sp = Utils_SharedPreferences(context)
        val bn = BN_Login()
        val body = BN_Login.ResultDataBean()
        body.apply {
            hxid = sp.getString(HXID, "")
            hxpassword = sp.getString(HXPASSWORD, "")
            token = sp.getString(TOKEN, "")
            user_name = sp.getString(USER_NAME, "")
        }
        bn.resultData = body
        return bn
    }

    fun getToken(context: Context): String {
        val sp = Utils_SharedPreferences(context)
        return sp.getString(TOKEN, "")
    }
}
