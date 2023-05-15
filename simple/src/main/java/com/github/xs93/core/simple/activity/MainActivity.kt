package com.github.xs93.core.simple.activity

import android.os.Bundle
import android.view.View
import com.github.xs93.core.base.ui.viewbinding.BaseVbActivity
import com.github.xs93.core.bus.FlowBus
import com.github.xs93.core.ktx.isStatusBarTranslucentCompat
import com.github.xs93.core.ktx.isSystemBarsTranslucentCompat
import com.github.xs93.core.simple.R
import com.github.xs93.core.simple.databinding.ActivityMainBinding
import com.github.xs93.core.simple.fragment.TestInsertsFragment

class MainActivity : BaseVbActivity<ActivityMainBinding>(R.layout.activity_main) {


    override fun initView(savedInstanceState: Bundle?) {
        window.apply {
            isStatusBarTranslucentCompat = true
            isSystemBarsTranslucentCompat = true
        }
        binding.surface = surface
        binding.clickEvent =
            ClickEvent()
        //        FlowBus.with<String>("TestBus").subscribe(this, Lifecycle.State.RESUMED, Dispatchers.Main) {
        ////            ToastUtils.show(it + " " + Thread.currentThread().name)
        //        }

        FlowBus.subscribe<String>("TestBus") {
            showToast(it + " " + Thread.currentThread().name)
        }

        FlowBus.subscribeSticky<String>("testSticky") {
            showToast(it + " " + Thread.currentThread().name)
        }
        //        FlowBus.withSticky<String>("testSticky").subscribe {
        //            ToastUtils.show(it)
        //        }
    }

    override fun initData(savedInstanceState: Bundle?) {

    }


    inner class ClickEvent {

        fun clickFullScreenDialog(view: View) {
            showToast("111111")
            showLoadingDialog()
//            val dialog = FullScreenDialogFragment()
//            dialog.showAllowingStateLoss(supportFragmentManager)
        }

        fun clickFragment() {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_frag_container, TestInsertsFragment.newInstance())
                .commitAllowingStateLoss()
        }
    }
}