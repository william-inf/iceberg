package as.williamthom.iceberg.settings

import as.williamthom.iceberg.common.APIController
import as.williamthom.iceberg.conf.ConfigProperties
import as.williamthom.iceberg.conf.Group
import as.williamthom.iceberg.exceptions.ObjectFailedValidationException
import groovy.util.logging.Slf4j
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.Status

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

    @Post("/config/group")
    @Patch("/config/group")
    @Produces(MediaType.TEXT_JSON)
    Group saveGroup(Group group) {
        log.info("Controller action POST/PATCH /config/group called ...")
        if (!group.isValid()) {
            throw new ObjectFailedValidationException<Group>(group)
        }

        settingsService.saveGroup(group)

        return group
    }

    @Delete("/config/group")
    @Status(HttpStatus.OK)
    @Produces(MediaType.TEXT_JSON)
    Map deleteGroup(String name) {
        log.info("Controller action DELETE /config/group called ...")

        settingsService.deleteGroup(name)
        return [success: true]
    }


}
