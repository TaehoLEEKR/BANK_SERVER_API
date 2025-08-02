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
    FAILED_TO_CALL_CLIENT(-101,"failed to call client"),
    CALL_REQUEST_BODY_NULL(-102,"call request body is null"),
    NOT_FOUND_PROVIDER(-103,"not found provider"),
    TOKEN_IS_INVALID(-104,"token is invalid"),
    TOKEN_IS_EXPIRE(-105,"auth token_is_expire"),
    FAILED_TO_INVOKE_INLOGGER(-106,"failed to invoke inlogger"),
    NOT_FOUND_USER(-107,"not found provider"),
    FAILED_TO_SAVE_DATA(-108,"failed to save data"),
    FAILED_TO_FIND_ACCOUNT(-109,"failed to find account"),
    MISS_MATCH_ACCOUNT_ULID_AND_USER_ULID(-110,"miss match account ulid and user ulid"),
    ACCOUNT_BALANCE_IS_NOT_ZERO(-111,"account_balance_is_not_zero"),
    FAILED_TO_INVOKE_WITH_MUTEX(-112,"failed to invoke with mutex"),
    FAILED_TO_INVOKE_WITHOUT_MUTEX(-113,"failed to invoke without mutex"),
    FAILED_TO_GET_LOCK(-114,"failed to get lock"),
    ;

}