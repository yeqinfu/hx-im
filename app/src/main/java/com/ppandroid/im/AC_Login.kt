package com.ppandroid.im

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.ppandroid.im.base.AC_Base
import com.ppandroid.im.bean.BN_Base
import com.ppandroid.im.bean.login.BN_Login
import com.ppandroid.im.bean.login.BN_Register
import com.ppandroid.im.utils.Contants
import com.ppandroid.im.utils.SnackbarUtils
import com.ppandroid.im.utils.TextUtils
import com.ppandroid.im.utils.Utils_Dialog
import com.zhy.http.okhttp.MyCallBack
import com.zhy.http.okhttp.OKUtils
import com.zhy.http.okhttp.OkHttpUtils
import com.zhy.http.okhttp.callback.StringCallback
import kotlinx.android.synthetic.main.activity_ac__login.*
import okhttp3.Call
import java.lang.Exception
import java.util.*

class AC_Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ac__login)
        btn_login.setOnClickListener {
            var userName = et_name.text.toString()
            var userPassword = et_password.text.toString()
            if (TextUtils.isEmpty(userName, userPassword)) {
                toast(getString(R.string.msg_empty))
            } else {
                toLogin(userName, userPassword)
            }
        }
        btn_register.setOnClickListener {
            var userName = et_name.text.toString()
            var userPassword = et_password.text.toString()
            if (TextUtils.isEmpty(userName, userPassword)) {
                toast(getString(R.string.msg_empty))
            } else {
                toRegister(userName, userPassword)
            }
        }
    }

    private fun toRegister(userName: String, userPassword: String) {
        var url = Contants.baseUrl + "hxCtrl/registerUser"
        OkHttpUtils
                .post()
                .url(url)
                .addParams("userName", userName)
                .addParams("userPassword", userPassword)
                .build()
                .execute(object : StringCallback() {
                    override fun onResponse(response: String?, id: Int) {
                        var gson = Gson()
                        var body = gson.fromJson(response, BN_Register::class.java) as BN_Register
                        if (body.resultCode == 0) {
                            toast(getString(R.string.msg_rigster_success))
                            toLogin(userName, userPassword)

                        } else {
                            toast(body.resultMessage)
                        }
                    }

                    override fun onError(call: Call?, e: Exception?, id: Int) {
                        e?.printStackTrace()
                    }

                })
    }

    private fun toLogin(userName: String, userPassword: String) {
        Utils_Dialog.showLoading(this)
        var url = Contants.baseUrl + "hxCtrl/loginSystem"
        var params = TreeMap<String, String>()
        params.apply {
            put("userName", userName)
            put("userPassword", userPassword)
        }
        OKUtils.post(this, url, params, BN_Login::class.java, object : MyCallBack<BN_Login> {

            override fun onResponse(response: BN_Login?) {
                Utils_Dialog.disMissLoading()
                response?.let {
                    toast(it.resultData?.token)
                }
            }

            override fun onError(error: BN_Base?) {
                Utils_Dialog.disMissLoading()
                error?.let {
                    toast(it.resultMessage)
                }
            }

        })
        /*  OkHttpUtils
                  .post()
                  .url(url)
                  .addParams("userName", userName)
                  .addParams("userPassword", userPassword)
                  .build()
                  .execute(object: StringCallback(){
                      override fun onResponse(response: String?, id: Int) {
                          var gson=Gson()
                          var body=gson.fromJson(response,BN_Login::class.java) as BN_Login

                          toast(response)
                      }

                      override fun onError(call: Call?, e: Exception?, id: Int) {
                      }

                  })*/
    }

    protected fun toast(msg: String?) {
        msg?.let {
            SnackbarUtils.with(AC_Base.getContentView(this)).setMessage(it).show()
        }

    }
}
