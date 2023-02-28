package com.github.xs93.core.base.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.*
import com.github.xs93.core.utils.AppInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 *
 * 基础ViewModel对象
 *
 * @author xushuai
 * @date   2022/5/5-21:21
 * @email  466911254@qq.com
 */
abstract class BaseViewModel<UiIntent : IUiIntent, UiState : IUIState, UiEvent : IUIEvent> : ViewModel(),
    LifecycleEventObserver {

    companion object {
        @JvmStatic
        fun createViewModelFactory(viewModel: ViewModel): ViewModelFactory {
            return ViewModelFactory(viewModel)
        }
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

    }

    //state 保存UI状态
    private val _uiStateFlow: MutableStateFlow<UiState> = MutableStateFlow(initUiState())
    val uiStateFlow: StateFlow<UiState> = _uiStateFlow
    protected abstract fun initUiState(): UiState

    //一次性消费事件，如toast，显示关闭弹窗等消息
    private val _uiEventFlow: Channel<CommonUiEvent<UiEvent>> = Channel()
    val uiEventFlow = _uiEventFlow.receiveAsFlow()

    private val _uiIntentFlow: Channel<UiIntent> = Channel()
    private val uiIntentFlow = _uiIntentFlow.receiveAsFlow()

    protected abstract fun handleIntent(intent: UiIntent)

    fun sendUiIntent(intent: UiIntent) {
        viewModelScope.launch {
            _uiIntentFlow.send(intent)
        }
    }

    protected fun setUiState(copy: UiState.() -> UiState) {
        _uiStateFlow.update {
            copy(_uiStateFlow.value)
        }
    }

    protected suspend fun sendUiEvent(uiEvent: UiEvent) {
        _uiEventFlow.send(CommonUiEvent.UserEvent(uiEvent))
    }

    protected fun getString(@StringRes resId: Int, vararg any: Any?): String {
        return AppInject.getApp().getString(resId, any)
    }

    protected fun showToast(charSequence: CharSequence) {
        viewModelScope.launch {
            _uiEventFlow.send(CommonUiEvent.Toast(charSequence))
        }
    }

    protected fun showToast(@StringRes resId: Int, vararg any: Any?) {
        viewModelScope.launch {
            _uiEventFlow.send(CommonUiEvent.Toast(AppInject.getApp().getString(resId)))
        }
    }

    protected fun showLoadingDialog() {
        viewModelScope.launch {
            _uiEventFlow.send(CommonUiEvent.ShowLoadingDialog(true))
        }
    }

    protected fun hideLoadingDialog() {
        viewModelScope.launch {
            _uiEventFlow.send(CommonUiEvent.ShowLoadingDialog(false))
        }
    }


    init {
        viewModelScope.launch {
            uiIntentFlow.collect {
                handleIntent(it)
            }
        }
    }
}

/**
 * ViewModel 工厂，这样可以外部传递参数给ViewModel
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val viewModel: ViewModel) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel as T
    }
}
