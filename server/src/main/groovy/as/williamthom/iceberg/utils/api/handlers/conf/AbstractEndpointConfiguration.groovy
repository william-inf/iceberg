package as.williamthom.iceberg.utils.api.handlers.conf

import as.williamthom.iceberg.conf.UrlEntry

abstract class AbstractEndpointConfiguration {
    String prefix
    String apiEndpoint
    String path

    AbstractEndpointConfiguration(UrlEntry entry) {
        this.prefix = entry.name
        this.apiEndpoint = entry.addr
        this.path = entry.path
    }

}
