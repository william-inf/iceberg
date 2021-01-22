package as.williamthom.iceberg.modules.common

import io.micronaut.http.HttpStatus

class ErrorResponse extends BaseAPIResponse {
    Map<String, String> errors = [:]

    ErrorResponse(HttpStatus status, String message) {
        super(status, message)
    }

    static ErrorResponse asBadRequest(String message, Map errors) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, message)
            .withErrors(errors)
    }

    static ErrorResponse asNotFound(String message, Map errors) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, message)
            .withErrors(errors)
    }

    ErrorResponse withErrors(Map errors) {
        this.errors = errors
        return this
    }

}

