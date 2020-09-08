package as.thomwilliam.utils.api.handlers

import as.thomwilliam.conf.UrlEntry
import as.thomwilliam.conf.UrlStatusResult
import as.thomwilliam.utils.api.EndpointAuthenticationType

interface EndpointHandler {

    UrlStatusResult callEndpoint(final UrlEntry urlEntry)

    EndpointAuthenticationType getEndpointType()

}
