@file:Suppress("unused")

package com.github.xs93.core.base.repository

/**
 * 基础数据仓库类
 *
 * @author XuShuai
 * @version v1.0
 * @date 2022/5/17 17:34
 */
open class BaseRepository {


    protected suspend fun <T> call(block: suspend () -> T?): T? {
        return try {
            block.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}