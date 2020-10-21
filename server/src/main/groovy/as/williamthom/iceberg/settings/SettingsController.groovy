package as.williamthom.iceberg.settings

import as.williamthom.iceberg.common.APIController
import as.williamthom.iceberg.conf.ConfigProperties
import as.williamthom.iceberg.conf.Group
import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.exceptions.ObjectFailedValidationException
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Status

import groovy.util.logging.Slf4j
import javax.inject.Inject

@Slf4j
@Controller("/api/settings")
class SettingsController extends APIController {

    @Inject SettingsService settingsService

    @Get("/config")
    @Produces(MediaType.TEXT_JSON)
    ConfigProperties getConfig() {
        log.info("Controller action /status called ...")
        return settingsService.getConfigProperties()
    }

    @Post("/config/urlEntry")
    @Patch("/config/urlEntry")
    @Produces(MediaType.TEXT_JSON)
    UrlEntry saveGroup(UrlEntry urlEntry) {
        log.info("Controller action POST/PATCH /config/urlEntry called ...")
        if (!urlEntry.isValid()) {
            throw new ObjectFailedValidationException<UrlEntry>(urlEntry)
        }

        settingsService.saveUrlEntry(urlEntry)

        return urlEntry
    }

    @Delete("/config/urlEntry/{name}")
    @Status(HttpStatus.OK)
    @Produces(MediaType.TEXT_JSON)
    Map deleteGroup(String name) {
        log.info("Controller action DELETE /config/group called ...")

        settingsService.deleteUrlEntry(name)
        return [success: true]
    }


}
