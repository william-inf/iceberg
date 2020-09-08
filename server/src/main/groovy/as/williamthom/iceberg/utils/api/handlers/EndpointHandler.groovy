package as.williamthom.iceberg.utils.api.handlers

import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.utils.api.EndpointAuthenticationType

interface EndpointHandler {

    UrlStatusResult callEndpoint(final UrlEntry urlEntry)

    EndpointAuthenticationType getEndpointType()

}
