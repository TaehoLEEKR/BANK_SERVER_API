package com.lecture.bank_server.common.exception

interface CodeInterface {
    val code:Int
    var message:String
}


enum class ErrorCode (
    override val code: Int,
    override var message: String,
) : CodeInterface {
    AUTH_CONFIG_NOT_FOUND(-100,"auth config not found"),
}