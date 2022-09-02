package com.github.xs93.core.simple

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.core.os.postDelayed
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import com.github.xs93.core.base.ui.viewbinding.BaseVbActivity
import com.github.xs93.core.bus.FlowBus
import com.github.xs93.core.ktx.isAllowForceDarkCompat
import com.github.xs93.core.ktx.isLightNavigationBarCompat
import com.github.xs93.core.ktx.isLightStatusBarsCompat
import com.github.xs93.core.ktx.isSystemBarsTranslucentCompat
import com.github.xs93.core.simple.databinding.ActivityMainBinding
import com.github.xs93.core.simple.dialog.FullScreenDialogFragment
import com.github.xs93.core.utils.toast.ToastUtils
import kotlinx.coroutines.Dispatchers

class MainActivity : BaseVbActivity<ActivityMainBinding>() {


    override fun beforeInitView(savedInstanceState: Bundle?) {
        super.beforeInitView(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
    }

    override fun initView(savedInstanceState: Bundle?) {
        ToastUtils.init(this)

        window.isSystemBarsTranslucentCompat = true
        window.isAllowForceDarkCompat = true
//        window.isLightStatusBarsCompat = true
//        window.isLightNavigationBarCompat = true

        mBinding.clickEvent = ClickEvent()
        mBinding.surface = surface
        window.apply {
            navigationBarColor = Color.BLUE
        }
        FlowBus.with<String>("TestBus").subscribe(this, Lifecycle.State.RESUMED, Dispatchers.Main) {
            ToastUtils.show(it + " " + Thread.currentThread().name)
        }

        FlowBus.withSticky<String>("testSticky").subscribe {
            ToastUtils.show(it)
        }
    }

    inner class ClickEvent {

        fun clickFullScreenDialog(view: View) {

//            FlowBus.with<String>("TestBus").post("测试bus")
            FlowBus.withSticky<String>("testSticky").post("测试Sticky")
            Handler(Looper.getMainLooper()).postDelayed(3000L) {
                val dialog = FullScreenDialogFragment()
                dialog.show(supportFragmentManager, "FullScreenDialogFragment")
            }

        }
    }
}