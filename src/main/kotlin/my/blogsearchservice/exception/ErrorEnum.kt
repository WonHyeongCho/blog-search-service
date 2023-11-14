package my.blogsearchservice.exception

import org.springframework.http.HttpStatus

enum class ErrorEnum(val errorCode: String, val errorMessage: String, val httpStatus: HttpStatus) {
    BAD_REQUEST("000", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),

    // API 에러
    API_UNAUTHORIZED("100", "서버 오류: 외부 API 인증 불가", HttpStatus.INTERNAL_SERVER_ERROR),
    API_BAD_REQUEST("101", "서버 오류: 잘못된 외부 API 요청", HttpStatus.INTERNAL_SERVER_ERROR),
    API_FORBIDDEN("102", "서버 오류: 외부 API 접근 금지", HttpStatus.INTERNAL_SERVER_ERROR),
    API_TOO_MANY_REQUESTS("103", "서버 오류: 외부 API 요청 횟수 초과", HttpStatus.INTERNAL_SERVER_ERROR),
    API_NOT_FOUND("104", "서버 오류: 외부 API 요청 데이터 없음", HttpStatus.INTERNAL_SERVER_ERROR),
    API_INTERNAL_SERVER_ERROR("105", "서버 오류: 외부 API 서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR),

    UNDEFINED("999", "알 수 없는 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
}
