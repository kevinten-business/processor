package io.processor.core.route.chain;

import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.key.RouteKey;

import java.util.List;

/**
 * The interface to get route event chain by route key.
 *
 * @param <Route> the route key type.
 * @param <Event> the route event type.
 */
public interface RouteEventChain<Route extends RouteKey<Event>, Event extends RouteEvent> {

    /**
     * Gets route event chain by route key.
     *
     * @param routeKey the route key
     * @return the route event chain
     */
    List<Event> getRouteEventChain(Route routeKey);
}
