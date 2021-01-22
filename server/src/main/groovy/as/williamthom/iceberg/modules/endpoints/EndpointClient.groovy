package as.williamthom.iceberg.modules.endpoints

import as.williamthom.iceberg.conf.UrlEntry
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe

@Client("/endpoint")
interface EndpointClient {

    @Get("/{code}")
    Maybe<UrlEntry> show(final String code)

}