package as.williamthom.iceberg.modules.common

import io.micronaut.http.HttpStatus

abstract class BaseAPIResponse {
    HttpStatus status
    String message

    BaseAPIResponse(HttpStatus status, String message) {
        this.status = status
        this.message = message
    }
}