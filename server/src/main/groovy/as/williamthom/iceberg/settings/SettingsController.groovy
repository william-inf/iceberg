package as.williamthom.iceberg.settings

import as.williamthom.iceberg.conf.ConfigManager
import as.williamthom.iceberg.conf.ConfigProperties
import groovy.util.logging.Slf4j
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post

import javax.inject.Inject

@Slf4j
@Controller("/api/settings")
class SettingsController {

    @Inject ConfigManager configManager

    @Get("/config")
    ConfigProperties getConfig() {
        log.info("Controller action /status called ...")
        return configManager.getConfigProperties()
    }

    @Patch("/config")
    def updateConfig() {

    }
}
