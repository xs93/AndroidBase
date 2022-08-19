package com.github.xs93.core.simple

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowCompat
import com.github.xs93.core.base.ui.viewbinding.BaseVbActivity
import com.github.xs93.core.simple.databinding.ActivityMainBinding
import com.github.xs93.core.simple.dialog.FullScreenDialogFragment

class MainActivity : BaseVbActivity<ActivityMainBinding>() {


    override fun beforeInitView(savedInstanceState: Bundle?) {
        super.beforeInitView(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, true)
    }

    override fun initView(savedInstanceState: Bundle?) {
        mBinding.clickEvent = ClickEvent()
        window.apply {
            navigationBarColor = Color.BLUE
        }
    }

    inner class ClickEvent {

        fun clickFullScreenDialog(view: View) {
            val dialog = FullScreenDialogFragment()
            dialog.show(supportFragmentManager, "FullScreenDialogFragment")
        }
    }
}