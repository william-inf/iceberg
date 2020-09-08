package as.thomwilliam.status

import as.thomwilliam.conf.ConfigManager
import as.thomwilliam.conf.UrlEntry
import as.thomwilliam.conf.UrlStatusResult
import as.thomwilliam.endpoints.EndpointService
import groovy.util.logging.Slf4j
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.reactivex.Single
import io.reactivex.Observable

import javax.inject.Inject

@Slf4j
@Controller("/status")
class StatusController {

    @Inject EndpointService endpointService
    @Inject StatusService statusService
    @Inject ConfigManager configManager

    @Get("/")
    Single<List<UrlStatusResult>> status() {
        log.info("Controller action /status called ...")
        return Observable.fromIterable(configManager.urls)
                .flatMap { UrlEntry entry -> statusService.fetchResult(entry).toObservable() }
                .toList() as Single<List<UrlStatusResult>>
    }

}
