package as.williamthom.iceberg.status

import as.williamthom.iceberg.conf.UrlStatusResult
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/status")
interface StatusClient {

    @Get("/")
    Single<List<UrlStatusResult>> status()

}