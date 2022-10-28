package com.bhm.supportcore

import android.content.Intent
import com.bhm.support.sdk.common.BaseVBActivity
import com.bhm.support.sdk.core.AppTheme
import com.bhm.supportcore.databinding.ActivityMainBinding

class TestActivity : BaseVBActivity<MainVM, ActivityMainBinding>() {

    override fun createViewModel() = MainVM(application)

    override fun initData() {
        super.initData()
        AppTheme.setStatusBarColor(this, R.color.purple_500)
    }

    override fun initEvent() {
        super.initEvent()
        viewBinding.btnOpen.setOnClickListener {
        }
    }
}