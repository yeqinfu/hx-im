package com.ppandroid.im.bean.mine;

import com.ppandroid.im.bean.BN_Base;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/1.
 */

public class BN_FindFriends extends BN_Base{

    private List<ResultDataBean> resultData;

    public List<ResultDataBean> getResultData() {
        return resultData;
    }

    public void setResultData(List<ResultDataBean> resultData) {
        this.resultData = resultData;
    }

    public static class ResultDataBean {
        /**
         * hxid : 2df4c8dfb93e4c6e8b67a700194d4d89
         * hxpassword : 28ce6c67-ad52-428f-b25c-29d140a37d9e
         * id : cbba1427-fc9d-49cb-9d65-8717dda1f059
         * password : 123456
         * user_name : 李
         */

        private String hxid;
        private String hxpassword;
        private String id;
        private String password;
        private String user_name;

        public String getHxid() {
            return hxid;
        }

        public void setHxid(String hxid) {
            this.hxid = hxid;
        }

        public String getHxpassword() {
            return hxpassword;
        }

        public void setHxpassword(String hxpassword) {
            this.hxpassword = hxpassword;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }
    }
}
