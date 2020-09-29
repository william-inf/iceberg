package as.williamthom.iceberg.conf

import javax.validation.Validation
import javax.validation.Validator

abstract class ValidatableObject<T extends ValidatableObject> {
    Map<String, String> errors = [:]

    Boolean isValid() {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator()
        Map<String, String> entries = validator
                .validate(this)
                .collectEntries {
                    [("${it.propertyPath}".toString()):  it.message ]
                }

        if (entries.keySet().size() > 0) {
            this.errors = entries
            return false
        }

        return true
    }

}
