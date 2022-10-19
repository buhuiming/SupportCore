package com.bhm.support.sdk.common

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.KeyEvent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.bhm.support.sdk.core.AppTheme
import com.bhm.support.sdk.core.WeakHandler
import com.bhm.support.sdk.entity.MessageEvent
import com.bhm.support.sdk.interfaces.ARCallBack
import com.bhm.support.sdk.interfaces.PermissionCallBack
import com.bhm.support.sdk.utils.ActivityUtil
import com.noober.background.BackgroundLibrary
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * @author Buhuiming
 * @description: Activity基类
 * @date :2022/6/28 14:09
 */
abstract class BaseActivity<VM : BaseViewModel> : AppCompatActivity(), Handler.Callback {

    lateinit var viewModel: VM

    private var activityLauncher: ActivityResultLauncher<Intent>? = null

    private var permissionLauncher: ActivityResultLauncher<Array<String>>? = null

    private var arCallBack: ARCallBack? = null

    private lateinit var permissionCallBack: PermissionCallBack

    lateinit var mainHandler: WeakHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        BackgroundLibrary.inject(this)
        super.onCreate(savedInstanceState)
        AppTheme.fitSystemWindow(this)
        ActivityUtil.addActivity(this)
        EventBus.getDefault().register(this)
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
        ActivityUtil.removeActivity(this)
        mainHandler.removeCallbacksAndMessages(null)
    }

    /**
     *ViewModel绑定
     */
    private fun init() {
        viewModel = createViewModel(this, createViewModel())
        activityLauncher = registerForActivityResult(StartActivityForResult()) { result ->
            if (result != null) {
                arCallBack?.call(result.resultCode, result.data)
            }
        }
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()
        ) {
            val resPermission: ArrayList<String> = ArrayList()
            it.keys.forEach { res ->
                if (it[res] == false) {
                    resPermission.add(res)
                }
            }

            if (resPermission.size > 0) {
                permissionCallBack.refuse(resPermission)
            } else {
                permissionCallBack.agree()
            }
        }
        mainHandler = WeakHandler(Looper.getMainLooper(), this)
    }

    /**
     * 创建ViewModel
     */
    abstract fun createViewModel(): VM

    /** 是否屏蔽返回键
     * @return
     */
    protected open fun isRefusedBackPress(): Boolean {
        return false
    }

    private fun createViewModel(owner: ViewModelStoreOwner, viewModel: VM): VM {
        return ViewModelProvider(owner).get(viewModel.javaClass)
    }

    fun startActivity(intent: Intent, arCallBack: ARCallBack) {
        this.arCallBack = arCallBack
        activityLauncher?.launch(intent)
    }

    fun requestPermission(permissions: Array<String>, callBack: PermissionCallBack) {
        permissionCallBack = callBack
        var allAgree = true
//        permissions.forEach { permission ->
//            allAgree = ContextCompat.checkSelfPermission(this, permission) !=
//                    PackageManager.PERMISSION_GRANTED
//        }
        for (permission in permissions){
            if( ContextCompat.checkSelfPermission(this, permission) !=
                PackageManager.PERMISSION_GRANTED){
                allAgree=false
                break
            }
        }
        if (allAgree) {
            permissionCallBack.agree()
            return
        }
        permissionLauncher?.launch(permissions)
    }

    override fun handleMessage(msg: Message): Boolean {
        return false
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onMessageEvent(event: MessageEvent?) {
       //EventBus Do something
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // TODO Auto-generated method stub
        if (isRefusedBackPress() && keyCode == KeyEvent.KEYCODE_BACK) {  //欢迎页 按物理返回键不能关闭APP
            return true
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }
}