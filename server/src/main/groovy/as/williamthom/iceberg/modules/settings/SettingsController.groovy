package as.williamthom.iceberg.modules.settings

import as.williamthom.iceberg.conf.ConfigProperties
import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlOrder
import as.williamthom.iceberg.conf.UrlReorderRequest
import as.williamthom.iceberg.modules.APIController
import as.williamthom.iceberg.modules.common.exceptions.ObjectFailedValidationException
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
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
    UrlEntry saveUrlEntry(UrlEntry urlEntry) {
        log.info("Controller action POST/PATCH /config/urlEntry called ...")
        if (!urlEntry.isValid()) {
            throw new ObjectFailedValidationException<UrlEntry>(urlEntry)
        }

        settingsService.saveUrlEntry(urlEntry)
        return urlEntry
    }

    @Post("/config/urlEntry/reorder")
    @Patch("/config/urlEntry/reorder")
    @Produces(MediaType.TEXT_JSON)
    Map reorderUrls(@Body List<UrlOrder> orderList) {
        log.info("Controller action POST/PATCH /config/urlEntry/reorder called ...")
        settingsService.updateUrlEntriesOrder(new UrlReorderRequest(orderList: orderList))

        return [:]
    }

    @Delete("/config/urlEntry/{name}")
    @Status(HttpStatus.OK)
    @Produces(MediaType.TEXT_JSON)
    Map deleteUrlEntry(String name) {
        log.info("Controller action DELETE /config/group called ...")

        settingsService.deleteUrlEntry(name)
        return [success: true]
    }


}
