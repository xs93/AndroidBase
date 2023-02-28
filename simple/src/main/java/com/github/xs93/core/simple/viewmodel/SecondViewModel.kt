package com.github.xs93.core.simple.viewmodel

import com.github.xs93.core.base.viewmodel.BaseViewModel
import com.github.xs93.core.simple.state.SecondViewEvent
import com.github.xs93.core.simple.state.SecondViewIntent
import com.github.xs93.core.simple.state.SecondViewState

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/2/27 10:23
 * @email 466911254@qq.com
 */
class SecondViewModel: BaseViewModel<SecondViewIntent, SecondViewState, SecondViewEvent>() {
    override fun initUiState(): SecondViewState {
        return SecondViewState()
    }

    override fun handleIntent(intent: SecondViewIntent) {
      when(intent){
          SecondViewIntent.InitData -> TODO()
      }
    }



    fun initData(){
        sendUiEvent(SecondViewEvent.T)
    }
}