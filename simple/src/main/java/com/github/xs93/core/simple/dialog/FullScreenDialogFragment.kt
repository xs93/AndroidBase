@file:Suppress("DEPRECATION")

package com.github.xs93.core.simple.dialog

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import com.github.xs93.core.base.ui.viewbinding.BaseVbDialogFragment
import com.github.xs93.core.bus.FlowBus
import com.github.xs93.core.ktx.isSystemBarsTranslucentCompat
import com.github.xs93.core.simple.R
import com.github.xs93.core.simple.databinding.DialogFullScreenBinding

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/12 10:17
 * @email 466911254@qq.com
 */
class FullScreenDialogFragment : BaseVbDialogFragment<DialogFullScreenBinding>(R.layout.dialog_full_screen) {

    override fun getStyle(): Int {
        return com.github.xs93.core.R.style.BaseFullScreenDialogStyle
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.suface = surface
        FlowBus.withSticky<String>("testSticky").subscribe(viewLifecycleOwner) {
            showToast(it + "FullScreenDialogFragment")
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            isSystemBarsTranslucentCompat = true
            setGravity(Gravity.BOTTOM)
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        }
    }

}