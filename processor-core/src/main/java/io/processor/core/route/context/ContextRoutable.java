package io.processor.core.route.context;

import io.processor.core.route.key.RouteKey;

/**
 * The interface allow Context routable.
 *
 * @param <Route> the route key type.
 */
public interface ContextRoutable<Route extends RouteKey> {

    /**
     * Supply context route key.
     *
     * @return the route key
     */
    Route supplyContextRouteKey();
}
