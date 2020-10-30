package as.williamthom.iceberg.utils.api.handlers.client

import as.williamthom.iceberg.conf.UrlEntry
import groovy.util.logging.Slf4j
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.RxHttpClient
import io.reactivex.Flowable

import javax.inject.Singleton
import java.security.cert.Certificate;

@Slf4j
@Singleton
class UnauthenticatedLowLevelClient {

    private final RxHttpClient _httpClient

    UnauthenticatedLowLevelClient(RxHttpClient httpClient) {
        _httpClient = httpClient
    }

    Flowable<HttpResponse<Map>> performGet(UrlEntry configuration) {
        HttpRequest<?> request = HttpRequest.GET("${configuration.addr}${configuration.path}".toString())

        if (configuration.authentication?.headers) {
            configuration.authentication.headers.each {Map<String, String> header ->
                header.keySet().each {
                    request.header(it, header[it])
                }
            }
        }

        Flowable<HttpResponse<Map>> flowable = _httpClient.exchange(request, Argument.of(Map.class), Argument.of(Map.class))

        return flowable as Flowable<HttpResponse<Map>>
    }

}
