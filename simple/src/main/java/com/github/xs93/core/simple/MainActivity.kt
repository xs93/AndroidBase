package com.github.xs93.core.simple

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.os.postDelayed
import androidx.lifecycle.Lifecycle
import com.github.xs93.core.base.ui.viewbinding.BaseVbActivity
import com.github.xs93.core.bus.FlowBus
import com.github.xs93.core.ktx.getColorCompat
import com.github.xs93.core.ktx.isSystemBarsTranslucentCompat
import com.github.xs93.core.ktx.launcher
import com.github.xs93.core.ktx.repeatOnLifecycle
import com.github.xs93.core.simple.databinding.ActivityMainBinding
import com.github.xs93.core.simple.dialog.FullScreenDialogFragment
import com.github.xs93.core.utils.toast.ToastUtils
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseVbActivity<ActivityMainBinding>() {


    override fun initView(savedInstanceState: Bundle?) {
        ToastUtils.init(this)
        mBinding.surface = surface
        window.isSystemBarsTranslucentCompat = true
        window.statusBarColor = getColorCompat(com.github.xs93.core.R.color.transparent)

        mBinding.clickEvent = ClickEvent()
//        FlowBus.with<String>("TestBus").subscribe(this, Lifecycle.State.RESUMED, Dispatchers.Main) {
////            ToastUtils.show(it + " " + Thread.currentThread().name)
//        }

        FlowBus.subscribe<String>("TestBus") {
            ToastUtils.show(it + " " + Thread.currentThread().name)
        }

        FlowBus.subscribeSticky<String>("testSticky") {
            ToastUtils.show(it + " " + Thread.currentThread().name)
        }
//        FlowBus.withSticky<String>("testSticky").subscribe {
//            ToastUtils.show(it)
//        }
    }

    inner class ClickEvent {

        fun clickFullScreenDialog(view: View) {
//            FlowBus.post("TestBus", "TestBus")
//            FlowBus.with<String>("TestBus").post("TestBus")
            FlowBus.postSticky("testSticky", "测试Sticky")
//            FlowBus.withSticky<String>("testSticky").post("测试Sticky")
            Handler(Looper.getMainLooper()).postDelayed(3000L) {
                val dialog = FullScreenDialogFragment()
                dialog.show(supportFragmentManager, "FullScreenDialogFragment")
            }

        }
    }
}