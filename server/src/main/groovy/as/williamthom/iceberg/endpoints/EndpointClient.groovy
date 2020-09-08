package as.thomwilliam.endpoints

import as.thomwilliam.conf.UrlEntry
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Maybe

@Client("/endpoint")
interface EndpointClient {

    @Get("/{code}")
    Maybe<UrlEntry> show(final String code)

}