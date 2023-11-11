package my.blogsearchservice.exception

import org.springframework.http.HttpStatus

enum class ErrorEnum(val errorCode: String, val errorMessage: String, val httpStatus: HttpStatus) {

    UNAUTHORIZED("000", "API 사용 권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    BAD_REQUEST ("001", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    UNDEFINED   ("999", "알 수 없는 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
}