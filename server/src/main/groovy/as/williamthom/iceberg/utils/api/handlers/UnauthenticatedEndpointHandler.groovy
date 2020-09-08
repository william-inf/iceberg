package as.thomwilliam.utils.api.handlers

import as.thomwilliam.conf.UrlEntry
import as.thomwilliam.conf.UrlStatusResult
import as.thomwilliam.utils.api.EndpointAuthenticationType
import as.thomwilliam.utils.api.handlers.client.UnauthenticatedLowLevelClient
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.exceptions.HttpClientResponseException
import javax.inject.Singleton;

@Slf4j
@Singleton
class UnauthenticatedEndpointHandler implements EndpointHandler {

    private final UnauthenticatedLowLevelClient client

    UnauthenticatedEndpointHandler(UnauthenticatedLowLevelClient unauthenticatedLowLevelClient) {
        this.client = unauthenticatedLowLevelClient
    }

    @Override
    UrlStatusResult callEndpoint(UrlEntry urlEntry) {
        try {
            HttpResponse response = client.performGet(urlEntry).blockingFirst()
            return UrlStatusResult.onSuccess(response, urlEntry).withBody(response.getBody(Map.class).get())
        } catch (HttpClientResponseException hcrex) {
            return UrlStatusResult.onSuccess(hcrex.response, urlEntry).withBody([error: hcrex.message])
        } catch (Exception ex) {
            log.error("${ex.class.name} calling addr ${urlEntry.addr}")
            return UrlStatusResult.onFailure(urlEntry).withBody([error: ex.message])
        }
    }

    @Override
    EndpointAuthenticationType getEndpointType() {
        return EndpointAuthenticationType.Unauthenticated
    }



}
