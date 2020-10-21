package as.williamthom.iceberg

import as.williamthom.iceberg.conf.ConfigManager
import as.williamthom.iceberg.conf.ConfigProperties
import as.williamthom.iceberg.utils.JSONUtils
import groovy.util.logging.Slf4j
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent

import javax.inject.Singleton;

@Slf4j
@Singleton
class DataLoader implements ApplicationEventListener<ServerStartupEvent>{

    static final String CONF_FILE_LOCATION = "/opt/iceberg/iceberg_config.conf"

    private ConfigManager configManager

    DataLoader(ConfigManager configManager) {
        this.configManager = configManager
    }

    @Override
    void onApplicationEvent(ServerStartupEvent event) {
        log.info("Loading config on start up event!")
        configManager.initialize()

        log.info("Config loaded successfully.")
    }

}
