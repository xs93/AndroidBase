package com.github.xs93.core.base.viewmodel

import androidx.annotation.Keep

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/2/24 10:03
 * @email 466911254@qq.com
 */


@Keep
interface IUIEvent

@Keep
sealed class CommonUiEvent<out T : IUIEvent> {
    data class Toast(val charSequence: CharSequence) : CommonUiEvent<Nothing>()
    data class ShowLoadingDialog(val show: Boolean) : CommonUiEvent<Nothing>()
    data class UserEvent<out T : IUIEvent>(val t: T) : CommonUiEvent<T>()
}

@Keep
interface IUiIntent

@Keep
interface IUIState
