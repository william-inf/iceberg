package as.williamthom.iceberg.backup

import as.williamthom.iceberg.conf.Backup
import as.williamthom.iceberg.conf.ConfigManager
import groovy.util.logging.Slf4j
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
final class BackupService {

    @Inject ConfigManager configManager

    Maybe<List<Backup>> findAll() {
        log.info("Retrieving all backups")

        return Maybe.just(configManager.backups)
                .subscribeOn(Schedulers.io())
    }

    Maybe<Backup> create() {
        log.info("Creating new backup")
        return Maybe.just(configManager.makeBackup())
               .subscribeOn(Schedulers.io())
    }

}
