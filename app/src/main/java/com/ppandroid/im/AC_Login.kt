package com.ppandroid.im

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ppandroid.im.base.AC_Base
import com.ppandroid.im.bean.BN_Base
import com.ppandroid.im.bean.ET_APP
import com.ppandroid.im.bean.login.BN_Login
import com.ppandroid.im.bean.login.BN_Register
import com.ppandroid.im.utils.*
import com.zhy.http.okhttp.MyCallBack
import com.zhy.http.okhttp.OKUtils
import kotlinx.android.synthetic.main.activity_ac__login.*
import org.greenrobot.eventbus.EventBus
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
        var params = TreeMap<String, String>()
        params.apply {
            put("userName", userName)
            put("userPassword", userPassword)
        }
        OKUtils.post(this,url,params,BN_Register::class.java,object :MyCallBack<BN_Register>{
            override fun onError(error: BN_Base?) {
                Utils_Dialog.disMissLoading()
                error?.let {
                    toast(it.resultMessage)
                }
            }

            override fun onResponse(response: BN_Register?) {
                response?.let {
                    toast(getString(R.string.msg_rigster_success))
                    toLogin(userName, userPassword)
                }
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
                    Utils_UserInfo.saveInfo(this@AC_Login,it)
                    EventBus.getDefault().post(ET_APP(ET_APP.TASKID_HX_LOGIN))
                    var it=Intent()
                    it.setClass(this@AC_Login,MainActivity::class.java)
                    startActivity(it)
                }
            }

            override fun onError(error: BN_Base?) {
                Utils_Dialog.disMissLoading()
                error?.let {
                    toast(it.resultMessage)
                }
            }

        })
    }

    private fun toast(msg: String?) {
        msg?.let {
            SnackbarUtils.with(AC_Base.getContentView(this)).setMessage(it).show()
        }

    }
}
