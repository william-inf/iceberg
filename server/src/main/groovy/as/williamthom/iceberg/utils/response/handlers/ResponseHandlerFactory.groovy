package as.thomwilliam.utils.response.handlers

import as.thomwilliam.utils.response.ResponseType
import groovy.util.logging.Slf4j
import io.micronaut.context.ApplicationContext

import javax.inject.Singleton

@Slf4j
@Singleton
class ResponseHandlerFactory {

    static private final Map<ResponseType, Class<? extends ResponseHandler>> _responseHandlers = [:]

    static {
        registerHandler(ResponseType.JSON, JSONResponseHandler)
    }

    private static void registerHandler(ResponseType type, Class<? extends ResponseHandler> bean) {
        _responseHandlers[type] = bean
    }

    private final ApplicationContext ctx

    ResponseHandlerFactory(ApplicationContext applicationContext) {
        this.ctx = applicationContext
    }

    ResponseHandler forType(final ResponseType type) throws RuntimeException {
        log.debug("Retrieving response handler for type ${type}")
        Class<? extends ResponseHandler> beanClazz = _responseHandlers[type]

        if (!beanClazz) {
            throw new RuntimeException("Missing handler for type - ${beanClazz.name}")
        }

        return getBean(beanClazz)
    }

    private <T> T getBean(Class<T> clazz) {
        T bean = (T) ctx.getBean(clazz)
        return bean
    }

}
