package com.lecture.bank_server.domains.transactions.controller

import com.lecture.bank_server.domains.transactions.domain.DepositResponse
import com.lecture.bank_server.domains.transactions.domain.TransferResponse
import com.lecture.bank_server.domains.transactions.model.DepositRequest
import com.lecture.bank_server.domains.transactions.model.TransferRequest
import com.lecture.bank_server.domains.transactions.service.TransactionService
import com.lecture.bank_server.model.dto.Response
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transactional")
class TransferController(
    val transactionService: TransactionService,
) {
    @PostMapping("/deposit")
    fun deposit(@RequestBody(required = true) request : DepositRequest) : Response<DepositResponse> {
        return transactionService.deposit(request.toUlid, request.toAccountId, request.value)
    }

    @PostMapping("/transfer")
    fun transfer(@RequestBody(required = true) request: TransferRequest) : Response<TransferResponse> {
        return transactionService.transfer(
            request.fromUlid,
            request.fromAccountId,
            request.toAccountId,
            request.value,
        )
    }


}