package as.thomwilliam.endpoints

import as.thomwilliam.conf.ConfigManager
import as.thomwilliam.conf.UrlEntry
import as.thomwilliam.utils.JSONUtils
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.reactivex.Maybe

import javax.inject.Inject

@Slf4j
@CompileStatic
@Controller("/endpoint")
class EndpointController {

    @Inject ConfigManager configManager
    @Inject EndpointService endpointService

    @Get("/")
    @Produces(MediaType.TEXT_JSON)
    Maybe<List<UrlEntry>> index() {
        return endpointService.findAll().onErrorComplete()
    }

    @Get("/{code}")
    @Produces(MediaType.TEXT_JSON)
    Maybe<UrlEntry> show(final String code) {
        log.info("Controller action endpoint/show/${code} called ...")
        return endpointService.findByCode(code)
    }
}
