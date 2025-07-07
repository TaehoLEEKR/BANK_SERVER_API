package com.lecture.bank_server.common.cache

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import kotlinx.serialization.Serializer
import org.redisson.api.RedissonClient
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisClient(
    private val template: RedisTemplate<String,String>,
    private val redissonClient : RedissonClient
) {

    fun get(key: String) : String? = template.opsForValue().get(key)

    fun <T> get(key: String, kSerializer: (Any) -> T?) : T?{
        val value = get(key)

        value?.let { return kSerializer(it) } ?: return null
    }

    fun setIfNotExist(key:String, value:String) = template.opsForValue().setIfAbsent(key, value) ?: false

    fun <T> invokeWithMutex(key : String , function : () -> T) :T? {

        val lock = redissonClient.getLock(key)
        var lockAcquired = false

        try{
             lockAcquired = lock.tryLock(10 ,15, TimeUnit.SECONDS)

            if(!lockAcquired) throw CustomException(ErrorCode.FAILED_TO_GET_LOCK, key)

            lock.lock(15 , TimeUnit.SECONDS)

            return function.invoke()

        }catch (e : Exception){
            throw CustomException(ErrorCode.FAILED_TO_INVOKE_WITH_MUTEX, e.message)
        }finally {

            if(lockAcquired && lock.isHeldByCurrentThread){
                lock.unlock()
            }

        }

    }
}