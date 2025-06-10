package com.lecture.bank_server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BankServerApplication

fun main(args: Array<String>) {
    runApplication<BankServerApplication>(*args)
}
