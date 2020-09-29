package as.williamthom.iceberg.exceptions

import as.williamthom.iceberg.conf.ValidatableObject

class ObjectFailedValidationException<T extends ValidatableObject<T>> extends RuntimeException {

    private T _object

    ObjectFailedValidationException(T object, String message = "Bad request!") {
        super(message)
        this._object = object
    }

    Map<String, String> getErrors() {
        return _object.errors
    }
}
