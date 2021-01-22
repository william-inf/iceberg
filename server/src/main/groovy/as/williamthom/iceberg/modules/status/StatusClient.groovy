package as.williamthom.iceberg.modules.status

import as.williamthom.iceberg.conf.UrlStatusResult
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/api/status")
interface StatusClient {

    @Get("/")
    Single<List<UrlStatusResult>> list()

}