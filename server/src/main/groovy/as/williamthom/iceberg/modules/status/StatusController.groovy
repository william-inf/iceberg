package as.williamthom.iceberg.modules.status

import as.williamthom.iceberg.utils.polling.UrlEntryPollResults
import as.williamthom.iceberg.conf.ConfigManager
import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.modules.endpoints.EndpointService
import groovy.util.logging.Slf4j
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

import javax.inject.Inject

@Slf4j
@Controller("/api/status")
class StatusController {

    @Inject EndpointService endpointService
    @Inject StatusService statusService
    @Inject ConfigManager configManager

    @Get("/")
    @Produces(MediaType.TEXT_JSON)
    Single<List<UrlStatusResult>> list() {
        log.info("Controller action /status called ...")
        return Observable.fromIterable(configManager.urls)
                .flatMap { UrlEntry entry -> statusService.fetchResult(entry).toObservable() }
                .toList() as Single<List<UrlStatusResult>>
    }

    @Get("/polling")
    @Produces(MediaType.TEXT_JSON)
    Maybe<List<UrlEntryPollResults>> allPolling() {
        log.info("Controller action /status/polling called ...")
        return statusService.getAllPollResults().onErrorComplete()
    }

    @Get("/{name}/polling")
    @Produces(MediaType.TEXT_JSON)
    Maybe<UrlEntryPollResults> polling(String name) {
        log.info("Controller action /status/polling/${name} called ...")
        return statusService.getPollResult(name).onErrorComplete()
    }

}
