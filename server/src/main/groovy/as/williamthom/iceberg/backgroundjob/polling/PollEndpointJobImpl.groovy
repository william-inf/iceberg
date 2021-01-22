package as.williamthom.iceberg.backgroundjob.polling

import as.williamthom.iceberg.backgroundjob.AbstractBackgroundJob
import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlStatusResult
import as.williamthom.iceberg.utils.JSONUtils
import as.williamthom.iceberg.utils.polling.PollFileHandler
import as.williamthom.iceberg.utils.polling.UrlEntryPollResults
import groovy.util.logging.Slf4j

import javax.inject.Inject
import java.time.LocalDate

@Slf4j
class PollEndpointJobImpl extends AbstractBackgroundJob<PollEndpointJobRequest, PollEndpointJobResponse> {

    @Override
    PollEndpointJobResponse performJob(PollEndpointJobRequest request) {
        log.info("Polling all endpoints ...")

        request.urlEntries.each { UrlEntry urlEntry ->
//            if (!urlEntry.pollStatus) return

            log.debug("Polling endpoint - ${urlEntry.name} @ [${urlEntry.addr}]")

            UrlStatusResult result = request.statusService.fetchResult(urlEntry).blockingGet()
            persistStatus(result)
        }

        return PollEndpointJobResponse.success()
    }

    private void persistStatus(UrlStatusResult result) {
        UrlEntryPollResults pollResultFile = PollFileHandler.getPollResultFromFile(result.urlEntry.addr, result.urlEntry.name)
        pollResultFile.addResult(result)

        PollFileHandler.savePollResult(pollResultFile)
    }



}





