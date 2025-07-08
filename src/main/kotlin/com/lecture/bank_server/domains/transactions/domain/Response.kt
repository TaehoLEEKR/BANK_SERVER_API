package com.lecture.bank_server.domains.transactions.domain

import com.lecture.bank_server.model.entity.Account
import java.math.BigDecimal
import java.math.BigInteger

data class DepositResponse(
    val afterBalance: BigDecimal,
)