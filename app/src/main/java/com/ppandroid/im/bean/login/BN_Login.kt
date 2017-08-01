package com.ppandroid.im.bean.login

import com.ppandroid.im.bean.BN_Base

/**
 * Created by yeqinfu on 2017/7/31.
 */

class BN_Login : BN_Base() {

    /**
     * resultData : {"hxid":"d6295b47-fb5c-46c3-977a-7010a5067b83","hxpassword":"ae3187f1-da22-4c54-b48e-2f43a52196fb","token":"BuTStElIUzPQxo0_","user_name":"老王"}
     */

    var resultData: ResultDataBean? = null

    class ResultDataBean {
        /**
         * hxid : d6295b47-fb5c-46c3-977a-7010a5067b83
         * hxpassword : ae3187f1-da22-4c54-b48e-2f43a52196fb
         * token : BuTStElIUzPQxo0_
         * user_name : 老王
         */

        var hxid: String? = null
        var hxpassword: String? = null
        var token: String? = null
        var user_name: String? = null
    }
}
