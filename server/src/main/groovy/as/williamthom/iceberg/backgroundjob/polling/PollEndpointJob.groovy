package as.williamthom.iceberg.backgroundjob.polling

import as.williamthom.iceberg.conf.ConfigManager
import as.williamthom.iceberg.modules.status.StatusService
import groovy.util.logging.Slf4j
import groovy.transform.CompileStatic
import io.micronaut.scheduling.annotation.Scheduled

import javax.inject.Inject
import javax.inject.Singleton

@CompileStatic
@Singleton
@Slf4j
class PollEndpointJob {

    @Inject ConfigManager configManager
    @Inject StatusService statusService

    void executeCleanup() {

    }

    @Scheduled(fixedDelay = "600s", initialDelay = "5s")
    void executePolling() {
        log.info "Polling endpoint list to check status..."
        new PollEndpointJobImpl().performJob(
            new PollEndpointJobRequest(configManager.urls, statusService),
        )
    }

}