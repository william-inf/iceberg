package as.williamthom.iceberg.utils.response.handlers

import as.williamthom.iceberg.conf.UrlResponseConfig
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.utils.response.ResponseType
import groovy.util.logging.Slf4j

import javax.inject.Singleton

@Slf4j
@Singleton
class PresenceResponseHandler implements ResponseHandler {

    Map handleResponse(final UrlStatusResult result) {
        return [status: result.status]
    }

    @Override
    ResponseType getResponseType() {
        return ResponseType.Presence
    }
}
