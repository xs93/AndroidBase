package com.github.xs93.core.base.loading.impl

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import com.github.xs93.core.base.loading.LoadingDialog
import com.github.xs93.core.base.loading.dialog.RealLoadingDialog

/**
 * 默认LoadingDialog
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/3/29 11:21
 * @email 466911254@qq.com
 */
class DefaultLoadingDialog(fm: FragmentManager, lifecycleOwner: LifecycleOwner) : LoadingDialog(fm, lifecycleOwner) {
    override fun createDialog(): DialogFragment {
        return RealLoadingDialog.newInstance()
    }
}