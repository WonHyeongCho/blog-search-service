package my.blogsearchservice.exception

import my.blogsearchservice.constant.logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(e: Exception): ResponseEntity<ErrorResponse> {
        logger().error("Exception occurred", e)
        val errorResponse = ErrorResponse(ErrorEnum.UNDEFINED)
        return ResponseEntity.status(ErrorEnum.UNDEFINED.httpStatus).body(errorResponse)
    }

    @ExceptionHandler(BlogSearchServiceException::class)
    fun handleSdkPolicyApiException(e: BlogSearchServiceException): ResponseEntity<ErrorResponse> {
        logger().error("BlogSearchServiceException occurred", e)
        val errorResponse = ErrorResponse(e.errorEnum)
        return ResponseEntity.status(e.errorEnum.httpStatus).body(errorResponse)
    }
}