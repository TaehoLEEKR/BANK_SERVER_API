package org.example.domains.history.repository

import com.lecture.bank_server.common.exception.CustomException
import com.lecture.bank_server.common.exception.ErrorCode
import com.lecture.bank_server.config.MongoTableCollector
import org.example.types.dto.History
import org.example.types.entity.TransactionHistoryDocument
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class HistoryMongoRepository(
    private val mongoTemplate: HashMap<String, MongoTemplate>,
    private val historyUserRepository: HistoryUserRepository,
    private val userNameMapper : ConcurrentHashMap<String, String> = ConcurrentHashMap(),
) {

    fun findLatestTransactionHistory(ulid : String) : List<History> {
        val criteria = Criteria().orOperator(
            Criteria.where("fromUlid").`is`(ulid),
            Criteria.where("toUlid").`is`(ulid)
        )

        val query = Query(criteria)
            .with(Sort.by(Sort.Direction.DESC, "time"))
            .limit(30)

        query.fields().exclude("_id")

        val result : List<TransactionHistoryDocument> = getTemplate(MongoTableCollector.Bank).find(query, TransactionHistoryDocument::class.java)

        return result.map { doc ->
            val fromUser = getUserName(doc.fromUlid)
            val toUser = getUserName(doc.toUlid)
            doc.toHistory(fromUser, toUser)
        }
    }

    private fun getUserName(ulid : String) : String {
        val value = userNameMapper[ulid] ?: ""

        if (value.isEmpty()) {
            val user = historyUserRepository.findByUlid(ulid)
            userNameMapper[ulid] = user.username
            return user.username
        } else {
            return value
        }
    }


    private fun getTemplate(c : MongoTableCollector) : MongoTemplate {
        val template = mongoTemplate[c.table] ?: throw CustomException(ErrorCode.FAILED_TO_FIND_MONGO_TEMPLATE, c.table)
        return template
    }

}