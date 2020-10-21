package as.williamthom.iceberg.conf

import as.williamthom.iceberg.utils.api.EndpointAuthentication

import javax.validation.constraints.NotNull

class UrlEntry extends ValidatableObject<UrlEntry> {
    @NotNull
    String addr

    @NotNull
    String name

    @NotNull
    String path

    @NotNull
    String group

    @NotNull
    EndpointAuthentication authentication

    @NotNull
    UrlResponseConfig response
}