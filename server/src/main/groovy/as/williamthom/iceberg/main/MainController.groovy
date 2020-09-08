package as.thomwilliam.main

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.views.View

@Controller("/")
class MainController {

    @Get("/")
    @View("index")
    HttpResponse index() {
        return HttpResponse.ok();
    }

}
