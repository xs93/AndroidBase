package com.github.xs93.core.simple.repository

import com.github.xs93.core.base.repository.BaseRepository
import kotlinx.coroutines.delay
import java.net.SocketTimeoutException

/**
 *
 *
 * @author XuShuai
 * @version v1.0
 * @date 2023/3/1 14:25
 * @email 466911254@qq.com
 */
class SecondRepository : BaseRepository() {


    suspend fun getUserInfo(): Int? {
        return call {
            getUser()
        }
    }

    @kotlin.jvm.Throws(SocketTimeoutException::class)
    private suspend fun getUser(): Int {
        delay(1000)
        return 0
    }
}