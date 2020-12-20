package io.processor.core.eventbus.event;

import com.kevinten.vrml.eventbus.event.process.AbstractAsyncProcessEvent;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.context.AbstractProcessorContext;

/**
 * The Abstract processor event.
 *
 * @param <Context>  the context type.
 * @param <Response> the response type.
 */
public abstract class AbstractProcessorEvent<Context extends AbstractProcessorContext, Response extends AbstractProcessorResponse>
        extends AbstractAsyncProcessEvent<Context, Response, AbstractProcessorEvent<Context, Response>> {

    /**
     * Instantiates a new Abstract processor event.
     *
     * @param source   the source
     * @param context  the context
     * @param response the response
     */
    public AbstractProcessorEvent(Object source, Context context, Response response) {
        super(source, context, response);
    }
}
