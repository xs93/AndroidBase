package com.github.xs93.core.loading

import androidx.fragment.app.DialogFragment

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/5/15 17:07
 * @email 466911254@qq.com
 */
interface IUiLoadingDialog {

    fun createLoadingDialog(): DialogFragment

    fun showLoadingDialog(message: String? = null)

    fun updateLoadingDialog(message: String)

    fun hideLoadingDialog()
}