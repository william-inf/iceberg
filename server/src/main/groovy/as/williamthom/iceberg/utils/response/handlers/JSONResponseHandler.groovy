package as.williamthom.iceberg.utils.response.handlers

import as.williamthom.iceberg.conf.UrlResponseConfig
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.utils.DynamicFinderMap
import as.williamthom.iceberg.utils.response.ResponseType
import groovy.util.logging.Slf4j

import javax.inject.Singleton

@Slf4j
@Singleton
class JSONResponseHandler implements ResponseHandler {

    Map handleResponse(final UrlStatusResult result) {
        try {
            log.info("Extracting details for ${result.urlEntry.name}")
            DynamicFinderMap body = new DynamicFinderMap(result.body)

            Map extracted = result.urlEntry.response.values.collectEntries {
                [(it.key): body?.get(it.key) ?: '<Missing>']
            }

            return extracted
        } catch (Exception ex) {
            log.error('Error!', ex)
            return [error: ex.message]
        }
    }

    @Override
    ResponseType getResponseType() {
        return ResponseType.JSON
    }

}
