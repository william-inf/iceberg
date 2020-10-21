package as.williamthom.iceberg.utils.api.handlers

import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.utils.api.EndpointAuthenticationType
import as.williamthom.iceberg.utils.api.handlers.client.UnauthenticatedLowLevelClient
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpResponse
import io.micronaut.http.client.exceptions.HttpClientResponseException

import javax.inject.Singleton

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
            return UrlStatusResult.onSuccess(response, urlEntry)
                    .withBody(response.body())
        } catch (HttpClientResponseException hcrex) {
            return UrlStatusResult.onSuccess(hcrex.response, urlEntry)
                    .withBody([error: hcrex.message])
        } catch (Exception ex) {
            log.error("${ex.class.name} calling addr ${urlEntry.addr}")
            return UrlStatusResult.onFailure(urlEntry)
                    .withBody([error: ex.message])
        }
    }

    @Override
    EndpointAuthenticationType getEndpointType() {
        return EndpointAuthenticationType.Unauthenticated
    }



}
