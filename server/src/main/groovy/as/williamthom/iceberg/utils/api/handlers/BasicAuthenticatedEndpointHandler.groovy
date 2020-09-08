package as.thomwilliam.utils.api.handlers

import as.thomwilliam.conf.UrlEntry
import as.thomwilliam.conf.UrlStatusResult
import as.thomwilliam.utils.api.EndpointAuthenticationType

class BasicAuthenticatedEndpointHandler implements EndpointHandler {

    @Override
    UrlStatusResult callEndpoint(UrlEntry urlEntry) {
        return null
    }

    @Override
    EndpointAuthenticationType getEndpointType() {
        return EndpointAuthenticationType.Basic
    }

}
