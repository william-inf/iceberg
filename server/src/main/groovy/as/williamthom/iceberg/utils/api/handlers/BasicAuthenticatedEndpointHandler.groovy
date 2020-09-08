package as.williamthom.iceberg.utils.api.handlers

import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.utils.api.EndpointAuthenticationType

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
