package com.github.xs93.core.base.viewmodel

import android.widget.Toast
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
sealed class CommonUiEvent : IUIEvent {
    data class ShowToast(val charSequence: CharSequence, val duration: Int = Toast.LENGTH_SHORT) : CommonUiEvent()

    data class ShowLoadingDialog(val show: Boolean) : CommonUiEvent()
}

@Keep
interface IUiIntent

@Keep
interface IUIState
