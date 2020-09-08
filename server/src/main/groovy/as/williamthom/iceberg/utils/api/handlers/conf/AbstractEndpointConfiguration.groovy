package as.thomwilliam.utils.api.handlers.conf

import as.thomwilliam.conf.UrlEntry

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
