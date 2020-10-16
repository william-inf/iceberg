package as.williamthom.iceberg.conf

import as.williamthom.iceberg.utils.response.handlers.ResponseHandler
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus

class UrlStatusResult {
    HttpStatus status
    UrlEntry urlEntry
    String group
    Map body
    Map values
    Date date

    static UrlStatusResult onSuccess(HttpResponse response, UrlEntry urlEntry) {
        UrlStatusResult urlStatusResult = new UrlStatusResult()
        urlStatusResult.status = response.getStatus()
        urlStatusResult.urlEntry = urlEntry
        urlStatusResult.group = urlEntry.group
        urlStatusResult.date = new Date()

        return urlStatusResult
    }

    static UrlStatusResult onFailure(UrlEntry urlEntry) {
        UrlStatusResult urlStatusResult = new UrlStatusResult()
        urlStatusResult.status = HttpStatus.INTERNAL_SERVER_ERROR
        urlStatusResult.urlEntry = urlEntry
        urlStatusResult.group = urlEntry.group
        urlStatusResult.date = new Date()

        return urlStatusResult
    }

    UrlStatusResult withBody(Map body) {
        this.body = body
        return this
    }

    UrlStatusResult withValues(Map values) {
        this.values = values
        return this
    }

    UrlStatusResult extractResponse(ResponseHandler handler)  {
        this.values = handler.handleResponse(this)

        return this
    }
}
