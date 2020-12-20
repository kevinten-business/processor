package io.processor.core.processor.annotation;

import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.response.StandardProcessorResponse;
import io.processor.core.route.key.RouteKey;

/**
 * The abstract standard processor.
 *
 * @param <Context> the context type.
 * @param <Route>   the route key type.
 */
public abstract class AbstractStandardAnnotationProcessor<Context extends AbstractProcessorContext, Route extends RouteKey>
        extends AbstractAnnotationProcessor<Context, StandardProcessorResponse, Route> {

    @Override
    public StandardProcessorResponse makeResponse() {
        return new StandardProcessorResponse();
    }
}
