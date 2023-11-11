package my.blogsearchservice.exception

import org.springframework.http.HttpStatus

enum class ErrorEnum(val errorCode: String, val errorMessage: String, val httpStatus: HttpStatus) {

    UNAUTHORIZED("000", "API 사용 권한이 없습니다.", HttpStatus.UNAUTHORIZED),
    BAD_REQUEST("001", "잘못된 요청입니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUNT("002", "요청하신 데이터를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),

    // API 에러
    API_UNAUTHORIZED("100", "서버 오류: 외부 API 인증 불가", HttpStatus.INTERNAL_SERVER_ERROR),
    API_BAD_REQUEST("101", "서버 오류: 잘못된 외부 API 요청", HttpStatus.INTERNAL_SERVER_ERROR),
    API_FORBIDDEN("102", "서버 오류: 외부 API 접근 금지", HttpStatus.INTERNAL_SERVER_ERROR),
    API_TOO_MANY_REQUESTS("103", "서버 오류: 외부 API 요청 횟수 초과", HttpStatus.INTERNAL_SERVER_ERROR),
    API_INTERNAL_SERVER_ERROR("104", "서버 오류: 외부 API 서버 내부 오류", HttpStatus.INTERNAL_SERVER_ERROR),

    UNDEFINED("999", "알 수 없는 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
}
