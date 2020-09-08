package as.williamthom.iceberg

import groovy.transform.CompileStatic
import io.micronaut.runtime.Micronaut

@CompileStatic
class Application {

    static void main(String[] args) {
        Micronaut.build(args)
            .eagerInitSingletons(true)
            .mainClass(Application.class)
            .start()
    }

}
