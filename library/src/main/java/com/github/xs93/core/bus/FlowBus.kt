@file:Suppress("UNCHECKED_CAST", "unused")

package com.github.xs93.core.bus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.set

/**
 * flow实现的事件总线
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/8/29 11:25
 * @email 466911254@qq.com
 */
object FlowBus {
    private val mEvents = ConcurrentHashMap<String, EventBus<*>>()
    private val mStickyEvents = ConcurrentHashMap<String, EventBus<*>>()

    @Synchronized
    fun <T> with(key: String): EventBus<T> {
        if (!mEvents.containsKey(key)) {
            mEvents[key] = EventBus<T>(key)
        }
        return mEvents[key] as EventBus<T>
    }

    @Synchronized
    fun <T> withSticky(key: String): StickyEventBus<T> {
        if (!mStickyEvents.containsKey(key)) {
            mStickyEvents[key] = StickyEventBus<T>(key)
        }
        return mStickyEvents[key] as StickyEventBus<T>
    }


    open class EventBus<T>(private val key: String) {
        private val _eventFlow: MutableSharedFlow<T> by lazy {
            obtainEvent()
        }

        open fun obtainEvent(): MutableSharedFlow<T> = MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)

        fun subscribe(
            dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
            block: (T) -> Unit
        ) {
            MainScope().launch(dispatcher) {
                _eventFlow.collect {
                    block(it)
                }
            }
        }

        fun subscribe(
            lifecycleOwner: LifecycleOwner,
            state: Lifecycle.State = Lifecycle.State.STARTED,
            dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
            block: (T) -> Unit
        ) {
            lifecycleOwner.lifecycleScope.launch(dispatcher) {
                lifecycleOwner.lifecycle.repeatOnLifecycle(state) {
                    _eventFlow.collect {
                        block(it)
                    }
                }
            }
        }

        fun post(value: T) {
            _eventFlow.tryEmit(value)
        }
    }

    class StickyEventBus<T>(key: String) : EventBus<T>(key) {
        override fun obtainEvent(): MutableSharedFlow<T> = MutableSharedFlow(1, 1, BufferOverflow.DROP_OLDEST)
    }
}