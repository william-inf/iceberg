package as.williamthom.iceberg.conf

import as.williamthom.iceberg.utils.JSONUtils

import javax.inject.Singleton
import java.time.LocalDate
import java.util.concurrent.ConcurrentHashMap;

@Singleton
class ConfigManager {

    static final String BACKUP_DIR_LOCATION = "/opt/iceberg/backups"
    static final String CONF_FILE_LOCATION = "/opt/iceberg/iceberg_config.conf"

    synchronized private ConfigProperties _properties

    void initialize() {
        File configFile = new File(CONF_FILE_LOCATION)
        if (!configFile.exists()) {
            throw new RuntimeException("Missing config file at location - ${CONF_FILE_LOCATION}")
        }

        _properties = JSONUtils.fromJson(configFile.text, ConfigProperties)
    }

    List<UrlEntry> getUrls() {
        return _properties.urls
    }

    List<Backup> getBackups() {
        File dir = new File(BACKUP_DIR_LOCATION)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        List<Backup> candidates = []

        dir.eachFile {
            candidates << new Backup(name: it.name, path: it.path, createdAt: it.lastModified())
        }

        return candidates
    }

    Backup makeBackup() {
        File dir = new File(BACKUP_DIR_LOCATION)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        File file = new File(BACKUP_DIR_LOCATION + "/iceberg_backup_conf-${LocalDate.now().toString()}".toString())
        file.write(JSONUtils.toJson(_properties))

        return new Backup(name: file.name, path: file.path, createdAt: file.lastModified())
    }

    ConcurrentHashMap<String, UrlEntry> getUrlMap() {
        def map = new ConcurrentHashMap<String, UrlEntry>()
        urls.each {map.put(it.name, it) }

        return map
    }

    ConfigProperties getConfigProperties() {
        return _properties
    }

    synchronized void saveUrlEntry(UrlEntry urlEntry) {
        _properties.saveUrlEntry(urlEntry)
        _properties.orderUrlEntries()
        persistConfig()
    }

    synchronized void updateUrlEntriesOrder(UrlReorderRequest reorderRequest) {
        _properties.updateUrlEntriesOrder(reorderRequest)

        persistConfig()
    }

    synchronized void deleteUrlEntry(String name) {
        _properties.removeUrlEntry(name)
        persistConfig()
    }

    private synchronized void persistConfig() {
        // Save to file system
        File file = new File(CONF_FILE_LOCATION)
        file.write(JSONUtils.toJson(_properties))
    }



}
