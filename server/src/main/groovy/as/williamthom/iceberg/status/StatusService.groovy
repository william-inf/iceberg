package as.williamthom.iceberg.status

import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.utils.api.handlers.EndpointHandler
import as.williamthom.iceberg.utils.api.handlers.EndpointHandlerFactory
import as.williamthom.iceberg.utils.response.handlers.ResponseHandler
import as.williamthom.iceberg.utils.response.handlers.ResponseHandlerFactory
import groovy.util.logging.Slf4j
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton
import java.util.function.Supplier

@Slf4j
@Singleton
class StatusService {

    @Inject EndpointHandlerFactory endpointHandlerFactory
    @Inject ResponseHandlerFactory responseHandlerFactory

    Maybe<UrlStatusResult> fetchResult(final UrlEntry entry) {
        log.info("Looking up status result for url ${entry.name}")
        return Maybe.just(entry)
            .subscribeOn(Schedulers.io())
            .map(it -> callUrlEntry(it).get())
            .onErrorComplete()
    }

    private Supplier<UrlStatusResult> callUrlEntry(final UrlEntry entry) {
        EndpointHandler endpointHandler = endpointHandlerFactory.forType(entry.authentication.type)
        ResponseHandler responseHandler = responseHandlerFactory.forType(entry.response.type)

        log.info("Calling into ${endpointHandler.getEndpointType().toString()}")

        return () -> {
            UrlStatusResult result = endpointHandler.callEndpoint(entry)
            Map values = responseHandler.handleResponse(result.urlEntry.response, result.body)

            result.withValues(values)

            return result
        }
    }
}
