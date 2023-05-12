package com.github.xs93.core.base.loading.base

import androidx.fragment.app.DialogFragment

/**
 * 基础类需要显示LoadingDialog 继承接口
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/4/26 10:17
 * @email 466911254@qq.com
 */
interface ILoadingDialog {

    fun showLoadingDialog()

    fun hideLoadingDialog()

    fun getLoadingDialog(): DialogFragment?
}