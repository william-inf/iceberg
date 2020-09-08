package as.thomwilliam.utils.response.handlers

import as.thomwilliam.conf.UrlEntry
import as.thomwilliam.conf.UrlResponseConfig
import as.thomwilliam.conf.UrlStatusResult
import as.thomwilliam.utils.response.ResponseType
import groovy.util.logging.Slf4j
import javax.inject.Singleton;

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
