package com.github.xs93.core.simple.state

import com.github.xs93.core.base.viewmodel.IUIEvent
import com.github.xs93.core.base.viewmodel.IUIState
import com.github.xs93.core.base.viewmodel.IUiIntent

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/2/27 10:21
 * @email 466911254@qq.com
 */
class SecondViewState : IUIState {
}


sealed class SecondViewEvent : IUIEvent {
    object InitData : SecondViewEvent()
}


sealed class SecondViewIntent : IUiIntent {

    object InitData : SecondViewIntent()
}