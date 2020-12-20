package io.processor.core.route.key;

import io.processor.core.route.event.RouteEvent;

/**
 * The Route key.
 *
 * @param <Event> the route event type.
 */
public interface RouteKey<Event extends RouteEvent> {

    /**
     * Gets route event.
     *
     * @return the route event
     */
    Event getRouteEvent();

    /**
     * Sets route event.
     *
     * @param routeEvent the route event
     */
    void setRouteEvent(Event routeEvent);

    // -- Rewrite equals() to match {@code Map.key}

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();
}
