package com.ppandroid.im

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ppandroid.im.utils.TextUtils
import com.ppandroid.im.utils.Utils_UserInfo

class AC_Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__welcome)
        var it=Intent()
        if (TextUtils.isEmpty(Utils_UserInfo.getToken(this@AC_Welcome))){
            it.setClass(this,AC_Login::class.java)
        }else{
            it.setClass(this,MainActivity::class.java)
        }
        startActivity(it)
    }
}
