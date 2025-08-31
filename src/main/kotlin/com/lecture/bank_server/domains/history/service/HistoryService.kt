package org.example.domains.history.service

import com.lecture.bank_server.common.cache.RedisClient
import com.lecture.bank_server.common.cache.RedisKeyProvider
import com.lecture.bank_server.common.json.JsonUtil
import com.lecture.bank_server.common.logging.Logging
import com.lecture.bank_server.model.dto.Response
import com.lecture.bank_server.model.dto.ResponseProvider
import kotlinx.serialization.builtins.ListSerializer
import org.example.domains.history.repository.HistoryMongoRepository
import org.example.types.dto.History
import org.slf4j.Logger
import org.springframework.stereotype.Service

@Service
class HistoryService(
    private val historyMongoRepository: HistoryMongoRepository,
    private val redisClient: RedisClient,
    private val logger : Logger = Logging.getLogger(HistoryService::class.java),
) {

    fun history(ulid : String) : Response<List<History>> = Logging.logFor(logger) { it ->
        it["ulid"] = ulid

        val key = RedisKeyProvider.historyCacheKey(ulid)
        val cacheValue = redisClient.get(key)

        return@logFor when {
            cacheValue == null -> {
                val result = historyMongoRepository.findLatestTransactionHistory(ulid)
                redisClient.setIfNotExist(key, JsonUtil.encodeToJson(result, ListSerializer(History.serializer())))
                return@logFor ResponseProvider.success(result)
            }

            else -> {
                val cachedData = JsonUtil.decodeToJson(cacheValue, ListSerializer(History.serializer()))
                return@logFor ResponseProvider.success(cachedData)
            }
        }
    }
}

