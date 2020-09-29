package as.williamthom.iceberg

import as.williamthom.iceberg.common.ErrorResponse
import as.williamthom.iceberg.conf.UrlEntry
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Error
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.reactivex.Maybe

@Slf4j
@CompileStatic
@Controller("/")
class GlobalController {

    @Get("/")
    @Produces(MediaType.TEXT_JSON)
    Map index() {
        return [sucess: true] as Map
    }

    @Produces(MediaType.TEXT_JSON)
    @Error(status = HttpStatus.NOT_FOUND, global = true)
    ErrorResponse onNotFound(HttpRequest request) {
        return ErrorResponse.asNotFound("Path [${request.path}] not found", [path: request.path])
    }
}
