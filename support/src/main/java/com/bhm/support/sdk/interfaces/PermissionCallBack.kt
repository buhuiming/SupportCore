package com.bhm.support.sdk.interfaces

/**
 * @author Buhuiming
 * @description: 权限申请回调
 * @date :2022/6/28 16:40
 */
interface PermissionCallBack {

    fun agree()

    fun refuse(refusePermissions: ArrayList<String>)
}