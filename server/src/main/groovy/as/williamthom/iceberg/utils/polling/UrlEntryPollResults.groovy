package as.williamthom.iceberg.utils.polling

import as.williamthom.iceberg.conf.UrlStatusResult

import java.time.LocalDateTime

class UrlEntryPollResults {
    String fileName
    String addr
    String name
    LocalDateTime lastRun

    List<UrlStatusResult> results = []

    UrlEntryPollResults(String addr, String name, String fileName) {
        this.addr = addr
        this.name = name
        this.fileName = fileName
    }

    void addResult(UrlStatusResult result) {
        this.results.add(0, result)

        while (this.results.size() > 10) {
            this.results.removeLast()
        }

        this.lastRun = LocalDateTime.now()
    }

    Integer getTotal() {
        return this.results.size()
    }
}