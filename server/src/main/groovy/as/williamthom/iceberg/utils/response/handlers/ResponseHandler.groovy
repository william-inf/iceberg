package as.thomwilliam.utils.response.handlers

import as.thomwilliam.conf.UrlResponseConfig
import as.thomwilliam.utils.response.ResponseType

interface ResponseHandler {

    Map handleResponse(final UrlResponseConfig config, final Map body)

    ResponseType getResponseType()

}
