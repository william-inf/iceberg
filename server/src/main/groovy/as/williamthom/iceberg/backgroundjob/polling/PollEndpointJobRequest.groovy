package as.williamthom.iceberg.backgroundjob.polling

import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.modules.status.StatusService

class PollEndpointJobRequest {
    List<UrlEntry> urlEntries
    StatusService statusService

    PollEndpointJobRequest(List<UrlEntry> urls, StatusService statusService) {
        this.urlEntries = urls
        this.statusService = statusService
    }
}