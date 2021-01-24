package io.processor.core.processor.annotation;

import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.processor.AbstractProcessor;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.route.key.RouteKey;

/**
 * The Abstract annotation processor.
 *
 * @param <Context>  the context type.
 * @param <Route>    the route key type.
 * @param <Response> the response parameter
 */
public abstract class AbstractAnnotationProcessor<Context extends AbstractProcessorContext, Response extends AbstractProcessorResponse, Route extends RouteKey>
        extends AbstractProcessor<Context, Response, Route> {
}
