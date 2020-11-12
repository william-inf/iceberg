package as.williamthom.iceberg.backup

import as.williamthom.iceberg.common.APIController
import as.williamthom.iceberg.conf.Backup
import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.endpoints.EndpointService
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.reactivex.Maybe

import javax.inject.Inject

@Slf4j
@CompileStatic
@Controller("/api/backup")
class BackupController extends APIController {

    @Inject BackupService backupService

    @Get("/")
    @Produces(MediaType.TEXT_JSON)
    Maybe<List<Backup>> index() {
        return backupService.findAll().onErrorComplete()
    }

    @Post("/")
    @Produces(MediaType.TEXT_JSON)
    Maybe<Backup> create() {
        return backupService.create()
    }

}
