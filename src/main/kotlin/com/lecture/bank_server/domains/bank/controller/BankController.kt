package com.lecture.bank_server.domains.bank.controller

import com.lecture.bank_server.domains.bank.service.BankService
import com.lecture.bank_server.model.dto.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/bank")
class BankController(
    private val bankService: BankService
) {
    @PostMapping("/create/{ulid}")
    fun createAccount(@PathVariable("ulid", required = true) ulid: String) : Response<String> = bankService.createAccount(ulid)

    @GetMapping("/balance/{ulid}/{accountUlid}")
    fun accountUlidance(@PathVariable("ulid", required = true) ulid: String, @PathVariable("accountUlid", required = true) accountUlid: String )
    : Response<String>{
        return bankService.balance(ulid,accountUlid)
    }

    @GetMapping("/remove/{ulid}/{accountUlid}")
    fun removeAccount(@PathVariable("ulid", required = true) ulid: String, @PathVariable("accountUlid", required = true) accountUlid: String )
    :Response<String>{
        return bankService.removeAccount(ulid,accountUlid);
    }
}