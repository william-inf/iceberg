package as.williamthom.iceberg.conf

import io.micronaut.http.HttpRequest
import org.junit.jupiter.api.Test

class ValidatableObjectSpec {

    @Test
    void testValidObject() {
        def validGroup = new Group(name: 'test', response: new UrlResponseConfig())
        assert(validGroup.isValid()).equals(true)
    }

    @Test
    void testInvalidObject() {
        def invalidGroup = new Group()
        def result = invalidGroup.isValid()

        assert(result).equals(false)
        assert(invalidGroup.errors.size()).equals(2)
    }

}
