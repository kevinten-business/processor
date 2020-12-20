package io.processor.core.processor;

import io.processor.core.context.ProcessorContext;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.route.key.RouteKey;

/**
 * This module is to process a context of different processor chain.
 *
 * @param <Context>  the context type.
 * @param <Response> the response type.
 * @param <Route>    the route key type.
 */
public interface Processor<Context extends ProcessorContext, Response extends AbstractProcessorResponse, Route extends RouteKey> {

    /**
     * Process a context.
     *
     * @param context the context to process.
     * @return the response, no mather this context is successful or not.
     */
    Response process(Context context);
}
