package as.thomwilliam.status

import as.thomwilliam.conf.UrlStatusResult
import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client
import io.reactivex.Single

@Client("/status")
interface StatusClient {

    @Get("/")
    Single<List<UrlStatusResult>> status()

}