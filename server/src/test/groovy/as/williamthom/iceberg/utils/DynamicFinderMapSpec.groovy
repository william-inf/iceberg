package as.williamthom.iceberg.utils

import org.junit.jupiter.api.Test

class DynamicFinderMapSpec {

    @Test
    void "Test deep search"() {
        def map = [a: [b: [c: "test"]]]
        def dfm = new DynamicFinderMap(map)

        assert(dfm.get("a.b.c")).equals("test")
    }

    @Test
    void "Test shallow search"() {
        def map = [a: "test"]
        def dfm = new DynamicFinderMap(map)

        assert(dfm.get("a")).equals("test")
    }
}
