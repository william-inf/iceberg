package as.williamthom.iceberg.conf

import as.williamthom.iceberg.utils.JSONUtils

import javax.inject.Singleton
import java.util.concurrent.ConcurrentHashMap;

@Singleton
class ConfigManager {

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
