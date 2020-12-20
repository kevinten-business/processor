package io.processor.core.context;

import com.kevinten.vrml.data.ability.Traceable;
import io.processor.core.route.key.RouteKey;
import io.processor.core.route.context.ContextRoutable;

/**
 * The Processor context.
 *
 * @param <Route> the route key type.
 */
public interface ProcessorContext<Route extends RouteKey>
        extends ContextRoutable<Route>, Traceable {
}
