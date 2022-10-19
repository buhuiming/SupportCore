package com.bhm.support.sdk.interfaces

import android.content.Intent

/**
 * @author Buhuiming
 * @description: 打开activity的回调
 * @date :2022/6/28 15:50
 */
interface ARCallBack {
    fun call(resultCode: Int, resultIntent: Intent?)
}