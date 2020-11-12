package as.williamthom.iceberg.settings

import as.williamthom.iceberg.conf.ConfigManager
import as.williamthom.iceberg.conf.ConfigProperties
import as.williamthom.iceberg.conf.Group
import as.williamthom.iceberg.conf.UrlEntry
import as.williamthom.iceberg.conf.UrlReorderRequest
import groovy.util.logging.Slf4j

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
final class SettingsService {

    @Inject ConfigManager configManager

    ConfigProperties getConfigProperties() {
        return configManager.getConfigProperties()
    }

    void saveUrlEntry(UrlEntry urlEntry) {
        configManager.saveUrlEntry(urlEntry)
    }

    void updateUrlEntriesOrder(UrlReorderRequest reorderRequest) {
        configManager.updateUrlEntriesOrder(reorderRequest)
    }

    void deleteUrlEntry(String name) {
        configManager.deleteUrlEntry(name)
    }
}
