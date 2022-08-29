@file:Suppress("DEPRECATION")

package com.github.xs93.core.simple.dialog

import android.annotation.TargetApi
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.github.xs93.core.base.ui.viewbinding.BaseVbDialogFragment
import com.github.xs93.core.bus.FlowBus
import com.github.xs93.core.simple.databinding.DialogFullScreenBinding
import com.github.xs93.core.utils.toast.ToastUtils

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/12 10:17
 * @email 466911254@qq.com
 */
class FullScreenDialogFragment : BaseVbDialogFragment<DialogFullScreenBinding>() {

    override fun getStyle(): Int {
        return com.github.xs93.core.R.style.BaseFullScreenDialogStyle
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        ToastUtils.init(requireContext().applicationContext)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        FlowBus.withSticky<String>("testSticky").subscribe(viewLifecycleOwner) {
            ToastUtils.show(it + "FullScreenDialogFragment")
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setGravity(Gravity.BOTTOM)
            WindowCompat.setDecorFitsSystemWindows(this, false)
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            statusBarColor = Color.WHITE
            navigationBarColor = Color.WHITE
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                navigationBarDividerColor = Color.TRANSPARENT
            }
            if (Build.VERSION.SDK_INT >= 28) {
                attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            }
            ViewCompat.setOnApplyWindowInsetsListener(mBinding.root) { v, insets ->
                val inset = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                mBinding.root.setPadding(inset.left, inset.top, inset.right, inset.bottom)
                insets
            }
        }
    }

}


var Window.isSystemBarsTranslucentCompat: Boolean
    get() {
        throw UnsupportedOperationException("set value only")
    }
    set(value) {
        if (Build.VERSION.SDK_INT >= 30) {
            setDecorFitsSystemWindows(!value)
        } else {
            val option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            decorView.systemUiVisibility =
                if (value) {
                    decorView.systemUiVisibility or option

                } else {
                    decorView.systemUiVisibility and option.inv()
                }
        }

        if (Build.VERSION.SDK_INT >= 28) {
            if (value) {
                attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            } else {
                attributes.layoutInDisplayCutoutMode =
                    WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_DEFAULT
            }
        }
    }

var Window.isLightStatusBarsCompat: Boolean
    get() {
        throw UnsupportedOperationException("set value only")
    }
    @TargetApi(23)
    set(value) {
        if (value) {
            if (Build.VERSION.SDK_INT >= 30) {
                decorView.windowInsetsController?.apply {
                    setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    )
                }
            } else {
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        } else {
            if (Build.VERSION.SDK_INT >= 30) {
                decorView.windowInsetsController?.apply {
                    setSystemBarsAppearance(
                        0,
                        WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                    )
                }
            } else {
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            }
        }
    }

var Window.isLightNavigationBarCompat: Boolean
    get() {
        throw UnsupportedOperationException("set value only")
    }
    @TargetApi(27)
    set(value) {
        if (value) {
            if (Build.VERSION.SDK_INT >= 30) {
                decorView.windowInsetsController?.apply {
                    setSystemBarsAppearance(
                        WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS,
                        WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
                    )
                }
            } else {
                decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
        } else {
            if (Build.VERSION.SDK_INT >= 30) {
                decorView.windowInsetsController?.apply {
                    setSystemBarsAppearance(0, WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS)
                }
            } else {
                decorView.systemUiVisibility =
                    decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }
    }

var Window.isAllowForceDarkCompat: Boolean
    get() {
        return if (Build.VERSION.SDK_INT >= 29) {
            decorView.isForceDarkAllowed
        } else {
            false
        }
    }
    set(value) {
        if (Build.VERSION.SDK_INT >= 29) {
            decorView.isForceDarkAllowed = value
        }
    }