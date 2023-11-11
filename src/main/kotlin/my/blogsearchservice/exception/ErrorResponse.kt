package my.blogsearchservice.exception

class ErrorResponse (
    var errorCode: String,
    var errorMessage: String
) {
    constructor(errorEnum: ErrorEnum) : this(errorEnum.errorCode, errorEnum.errorMessage)
}