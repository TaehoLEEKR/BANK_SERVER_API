package com.lecture.bank_server.common.transaction

import jakarta.transaction.Transactional
import org.springframework.stereotype.Component

@Component
class Transactional (

){
    @Transactional()
    fun run(){

    }
}