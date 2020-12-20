package io.processor.core.route.key;

import io.processor.core.route.event.RouteEvent;

/**
 * The Abstract route key.
 *
 * @param <Event> the route event type.
 */
public abstract class AbstractRouteKey<Event extends RouteEvent> implements RouteKey<Event> {

    /**
     * The Route event.
     */
    protected Event routeEvent;

    /**
     * Instantiates a new Abstract route key.
     */
    public AbstractRouteKey() {
    }

    /**
     * Instantiates a new Abstract route key.
     *
     * @param routeEvent the route event
     */
    public AbstractRouteKey(Event routeEvent) {
        this.routeEvent = routeEvent;
    }

    @Override
    public Event getRouteEvent() {
        return routeEvent;
    }

    @Override
    public void setRouteEvent(Event routeEvent) {
        this.routeEvent = routeEvent;
    }

    // -- Rewrite equals() to match {@code Map.key}

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract int hashCode();
}
