package io.processor.core.processor.interfacee;

import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.response.StandardProcessorResponse;
import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.key.RouteKey;

import java.util.Collections;
import java.util.List;

/**
 * The abstract standard processor.
 *
 * @param <Context> the context type.
 * @param <Route>   the route key type.
 */
public abstract class AbstractStandardInterfaceProcessor<Context extends AbstractProcessorContext, Route extends RouteKey>
        extends AbstractInterfaceProcessor<Context, StandardProcessorResponse, Route> {

    @Override
    public StandardProcessorResponse makeResponse() {
        return new StandardProcessorResponse();
    }

    @Override
    public List<Route> supplyProcessorRouteKeys() {
        return Collections.singletonList(this.supplyRouteKey());
    }

    /**
     * Supply route key.
     *
     * @return the route key
     */
    protected abstract Route supplyRouteKey();

    /**
     * Processor needs to determine the routing order based on {@code RouteEvent}
     */
    protected abstract RouteEvent routeEvent();
}
