package as.williamthom.iceberg.main

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View

@Controller("/")
class MainController {

    @Get("/")
    HttpResponse index() {
        return HttpResponse.ok();
    }

}
