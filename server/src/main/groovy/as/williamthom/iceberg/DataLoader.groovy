package as.thomwilliam

import as.thomwilliam.conf.ConfigManager
import as.thomwilliam.conf.ConfigProperties
import as.thomwilliam.utils.JSONUtils
import groovy.util.logging.Slf4j
import io.micronaut.context.event.ApplicationEventListener
import io.micronaut.runtime.server.event.ServerStartupEvent

import javax.inject.Singleton;

@Slf4j
@Singleton
class DataLoader implements ApplicationEventListener<ServerStartupEvent>{

    static final String CONF_FILE_LOCATION = "/opt/status_page/status_config.conf"

    private ConfigManager configManager

    DataLoader(ConfigManager configManager) {
        this.configManager = configManager
    }

    @Override
    void onApplicationEvent(ServerStartupEvent event) {
        log.info("Loading config on start up event!")
        File configFile = new File(CONF_FILE_LOCATION)
        if (!configFile.exists()) {
            throw new RuntimeException("Missing config file at location - ${CONF_FILE_LOCATION}")
        }

        ConfigProperties conf = JSONUtils.fromJson(configFile.text, ConfigProperties)
        configManager.initialize(conf)

        log.info("Config loaded successfully.")
    }

}
