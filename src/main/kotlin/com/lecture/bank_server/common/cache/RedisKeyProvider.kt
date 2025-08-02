package com.lecture.bank_server.common.cache

object RedisKeyProvider {
    private const val BANK_MUTEX_KEY ="bankMutex"
    private const val HISTORY_CACHE_KEY = "history"

    fun bankMutexKey(ulid: String, accountUlid: String) = "$BANK_MUTEX_KEY:$ulid:$accountUlid"
    fun historyCacheKey(ulid: String, accountUlid: String) = "$HISTORY_CACHE_KEY:$ulid:$accountUlid"
}