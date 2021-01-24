package io.processor.core.processor.interfacee;

import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.processor.AbstractProcessor;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.route.interfacee.ProcessorRoutable;
import io.processor.core.route.key.RouteKey;

/**
 * The Abstract interface processor.
 *
 * @param <Context>  the context type.
 * @param <Route>    the route key type.
 * @param <Response> the response parameter
 */
public abstract class AbstractInterfaceProcessor<Context extends AbstractProcessorContext, Response extends AbstractProcessorResponse, Route extends RouteKey>
        extends AbstractProcessor<Context, Response, Route>
        implements ProcessorRoutable<Route> {
}
