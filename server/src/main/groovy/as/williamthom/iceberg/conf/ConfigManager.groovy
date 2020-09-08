package as.thomwilliam.conf

import javax.inject.Singleton
import java.util.concurrent.ConcurrentHashMap;

@Singleton
class ConfigManager {

    synchronized private ConfigProperties _properties

    void initialize(ConfigProperties properties) {
        _properties = properties
    }

    List<UrlEntry> getUrls() {
        return _properties.urls
    }

    ConcurrentHashMap<String, UrlEntry> getUrlMap() {
        def map = new ConcurrentHashMap<String, UrlEntry>()
        urls.each {map.put(it.name, it) }

        return map
    }

    synchronized void persistConfig() {

    }

}
