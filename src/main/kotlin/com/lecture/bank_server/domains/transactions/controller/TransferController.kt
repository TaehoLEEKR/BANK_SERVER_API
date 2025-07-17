package com.lecture.bank_server.domains.transactions.controller

import com.lecture.bank_server.domains.transactions.domain.DepositResponse
import com.lecture.bank_server.domains.transactions.domain.TransferResponse
import com.lecture.bank_server.domains.transactions.service.TransactionService
import com.lecture.bank_server.model.dto.Response
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/transactional")
class TransferController(
    val transactionService: TransactionService,
) {
    @PostMapping("/deposit")
    fun deposit() : Response<DepositResponse> {
        return transactionService.deposit()
    }

    @PostMapping("/transfer")
    fun transfer() : Response<TransferResponse> {
        return transactionService.transfer()
    }


}