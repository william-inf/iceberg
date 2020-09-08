package as.williamthom.iceberg.utils.response.handlers

import as.williamthom.iceberg.conf.UrlResponseConfig
import as.williamthom.iceberg.utils.response.ResponseType
import groovy.util.logging.Slf4j

import javax.inject.Singleton

@Slf4j
@Singleton
class JSONResponseHandler implements ResponseHandler {

    Map handleResponse(final UrlResponseConfig config, final Map body) {
        Map result = config.values.collectEntries {
            [(it.label): body.get(it.key, "<Missing>")]
        }

        return result
    }

    @Override
    ResponseType getResponseType() {
        return ResponseType.JSON
    }
}
