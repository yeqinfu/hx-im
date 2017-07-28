package com.ppandroid.im

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ppandroid.im.base.AC_Base
import com.ppandroid.im.utils.Contants
import com.ppandroid.im.utils.SnackbarUtils
import com.ppandroid.im.utils.TextUtils
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_ac__login.*
import okhttp3.Call
import java.lang.Exception

class AC_Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__login)
        btn_login.setOnClickListener {
            var userName=et_name.text.toString()
            var userPassword=et_password.text.toString()
            if (TextUtils.isEmpty(userName,userPassword)){
                toast(getString(R.string.msg_empty))
            }else{
                toLogin(userName,userPassword)
            }
        }
        btn_register.setOnClickListener {
            var userName=et_name.text.toString()
            var userPassword=et_password.text.toString()
            if (TextUtils.isEmpty(userName,userPassword)){
                toast(getString(R.string.msg_empty))
            }else{
                toRegister(userName,userPassword)
            }
        }
    }

    private fun  toRegister(userName: String, userPassword: String) {
        var url= Contants.baseUrl+"hxCtrl/registerUser"
        OkHttpUtils
                .post()
                .url(url)
                .addParams("userName", userName)
                .addParams("userPassword", userPassword)
                .build()
                .execute(object: StringCallback(){
                    override fun onResponse(response: String?, id: Int) {
                        toast(response)
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                    }

                })
    }

    private fun  toLogin(userName: String, userPassword: String) {
        var url= Contants.baseUrl+"hxCtrl/loginSystem"
        OkHttpUtils
                .post()
                .url(url)
                .addParams("userName", userName)
                .addParams("userPassword", userPassword)
                .build()
                .execute(object: StringCallback(){
                    override fun onResponse(response: String?, id: Int) {
                        toast(response)
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                    }

                })
    }
    protected fun toast(msg: String?) {
         msg?.let {
             SnackbarUtils.with(AC_Base.getContentView(this)).setMessage(it).show()
         }

    }
}
