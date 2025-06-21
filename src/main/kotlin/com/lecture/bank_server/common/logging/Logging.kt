package com.lecture.bank_server.common.logging
import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import org.slf4j.*

// AOP

object Logging{
    fun<T: Any> getLogger(clazz: Class<T>) : Logger = LoggerFactory.getLogger(clazz)

    fun <T> logFor(log: Logger, function: () -> T?) : T {
        val logInfo = mutableMapOf<String,Any>()
        logInfo["start_at"] = now()

        val result = function.invoke()

        logInfo["end_at"] = now()

        log.info(logInfo.toString())

        result ?: throw CustomException(ErrorCode.FAILED_TO_INVOKE_INLOGGER)
    }

    private fun now() : Long {
        return System.currentTimeMillis()
    }
}