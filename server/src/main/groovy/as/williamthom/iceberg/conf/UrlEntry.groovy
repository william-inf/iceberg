package as.williamthom.iceberg.conf

import as.williamthom.iceberg.utils.api.EndpointAuthenticationType

class UrlEntry {
    String addr
    String name
    String path
    EndpointAuthenticationType type
    UrlResponseConfig response

    Map toMap() {
        return [
            addr: addr,
            name: name,
            path: path,
            type: type
        ]
    }
}