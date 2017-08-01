package com.ppandroid.im.bean

import com.ppandroid.im.base.ET_Base
import java.util.*

/**
 * Created by yeqinfu on 2017/8/1.
 */

class ET_APP(taskId: Int) : ET_Base(taskId) {
    companion object {
        val TASKID_HX_LOGIN = UUID.randomUUID().hashCode()
    }
}
