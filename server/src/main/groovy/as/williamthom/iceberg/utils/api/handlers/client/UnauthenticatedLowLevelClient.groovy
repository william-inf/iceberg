package as.thomwilliam.utils.api.handlers.client

import as.thomwilliam.conf.UrlEntry
import groovy.util.logging.Slf4j
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Flowable

import javax.inject.Singleton;

@Slf4j
@Singleton
class UnauthenticatedLowLevelClient {

    private final RxHttpClient httpClient

    UnauthenticatedLowLevelClient(RxHttpClient httpClient) {
        this.httpClient = httpClient
    }

    Flowable<HttpResponse<Map>> performGet(UrlEntry configuration) {
        HttpRequest<?> request = HttpRequest.GET("${configuration.addr}${configuration.path}".toString())
        Flowable<HttpResponse<Map>> flowable = httpClient.exchange(request, Argument.of(Map.class), Argument.of(Map.class))
        return flowable as Flowable<HttpResponse<Map>>
    }

}