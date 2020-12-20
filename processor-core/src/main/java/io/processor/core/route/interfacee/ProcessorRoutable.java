package io.processor.core.route.interfacee;

import io.processor.core.route.key.RouteKey;

import java.util.List;

/**
 * The interface allow Processor routable.
 *
 * @param <Route> the route key type.
 */
public interface ProcessorRoutable<Route extends RouteKey> {

    /**
     * Supply processor route key list.
     *
     * @return the route key list
     */
    List<Route> supplyProcessorRouteKeys();
}
