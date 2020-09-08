package as.williamthom.iceberg.utils.api.handlers

import as.williamthom.iceberg.utils.api.EndpointAuthenticationType
import groovy.util.logging.Slf4j
import io.micronaut.context.ApplicationContext

import javax.inject.Singleton

@Slf4j
@Singleton
class EndpointHandlerFactory {

    static private final Map<EndpointAuthenticationType, Class<? extends EndpointHandler>> _endpointHandlers = [:]

    static {
        registerHandler(EndpointAuthenticationType.Unauthenticated, UnauthenticatedEndpointHandler)
        registerHandler(EndpointAuthenticationType.Basic, BasicAuthenticatedEndpointHandler)
    }

    private static void registerHandler(EndpointAuthenticationType type, Class<? extends EndpointHandler> bean) {
        _endpointHandlers[type] = bean
    }

    private final ApplicationContext ctx

    EndpointHandlerFactory(ApplicationContext applicationContext) {
        this.ctx = applicationContext
    }

    EndpointHandler forType(final EndpointAuthenticationType type) throws RuntimeException {
        log.debug("Retrieving handler for type ${type}")
        Class<? extends EndpointHandler> beanClazz = _endpointHandlers[type]

        if (!beanClazz) {
            throw new RuntimeException("Missing handler for endpoint authentication type - ${beanClazz.name}")
        }

        return getBean(beanClazz)
    }

    private <T> T getBean(Class<T> clazz) {
        T bean = (T) ctx.getBean(clazz)
        return bean
    }

}
