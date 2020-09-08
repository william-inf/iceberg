package as.williamthom.iceberg.endpoints

import as.williamthom.iceberg.conf.ConfigManager
import as.williamthom.iceberg.conf.UrlEntry
import groovy.util.logging.Slf4j
import io.reactivex.Maybe
import io.reactivex.schedulers.Schedulers

import javax.inject.Inject
import javax.inject.Singleton
import java.util.concurrent.ConcurrentHashMap

@Slf4j
@Singleton
final class EndpointService {

    @Inject ConfigManager configManager

    Maybe<List<UrlEntry>> findAll() {
        log.info("Retrieving all URL entries")

        return Maybe.just(configManager.urls)
                .subscribeOn(Schedulers.io())
    }

    Maybe<UrlEntry> findByCode(final String code) {
        log.info("Looking up URL for code ${code}")

        ConcurrentHashMap<String, UrlEntry> urls = configManager.getUrlMap()

        return Maybe.just(code)
            .subscribeOn(Schedulers.io())
            .map {String it -> urls.getOrDefault(it, new UrlEntry()) } as Maybe<UrlEntry>
    }

}
