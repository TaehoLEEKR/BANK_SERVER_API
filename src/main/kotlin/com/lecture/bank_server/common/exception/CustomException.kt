package com.lecture.bank_server.common.exception


class CustomException(
    private val codeInterface: CodeInterface,
    private val additationalMessage: String? = null,
) : RuntimeException(
    if(additationalMessage == null) codeInterface.message else "${codeInterface.message} - $additationalMessage"
)