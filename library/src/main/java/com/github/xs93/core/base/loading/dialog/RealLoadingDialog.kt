package com.github.xs93.core.base.loading.dialog

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import com.github.xs93.core.R
import com.github.xs93.core.base.ui.viewbinding.BaseVbDialogFragment
import com.github.xs93.core.databinding.BaseLayoutLoadingBinding

/**
 * 加载弹出框
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/7/26 17:27
 * @email 466911254@qq.com
 */
class RealLoadingDialog : BaseVbDialogFragment<BaseLayoutLoadingBinding>(R.layout.base_layout_loading) {

    companion object {
        fun newInstance(): RealLoadingDialog {
            val args = Bundle()
            val fragment = RealLoadingDialog()
            fragment.arguments = args
            return fragment
        }
    }

    @SuppressLint("DiscouragedApi")
    override fun initView(view: View, savedInstanceState: Bundle?) {
        dialog?.apply {
            setCanceledOnTouchOutside(false)
            setCancelable(false)
        }
    }
}