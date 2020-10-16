package as.williamthom.iceberg.conf

import as.williamthom.iceberg.utils.api.EndpointAuthentication

class UrlEntry {
    String addr
    String name
    String path
    String group
    EndpointAuthentication authentication
    UrlResponseConfig response
}