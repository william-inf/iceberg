package as.williamthom.iceberg.modules

import as.williamthom.iceberg.modules.common.ErrorResponse
import as.williamthom.iceberg.modules.common.exceptions.ObjectFailedValidationException
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Status

abstract class APIController {

    @Produces(MediaType.TEXT_JSON)
    @Status(HttpStatus.BAD_REQUEST)
    @Error(exception = ObjectFailedValidationException.class)
    ErrorResponse onValidationFailed(HttpRequest request, ObjectFailedValidationException ofve) {
        return ErrorResponse.asBadRequest(ofve.message, ofve.errors)
    }

}
