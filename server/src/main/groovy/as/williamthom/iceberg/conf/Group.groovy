package as.williamthom.iceberg.conf

import javax.validation.constraints.NotNull

class Group extends ValidatableObject<Group> {

    @NotNull
    String name

    @NotNull
    UrlResponseConfig response

}
