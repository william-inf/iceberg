package as.williamthom.iceberg.utils.response.handlers

import as.williamthom.iceberg.conf.UrlResponseConfig
import as.williamthom.iceberg.utils.response.ResponseType

interface ResponseHandler {

    Map handleResponse(final UrlResponseConfig config, final Map body)

    ResponseType getResponseType()

}
