package io.processor.core.receiver;

import io.processor.core.response.StandardProcessorResponse;
import io.processor.core.context.AbstractProcessorContext;

/**
 * The abstract standard receiver.
 *
 * @param <Context> the context type.
 */
public abstract class AbstractStandardReceiver<Context extends AbstractProcessorContext>
        extends AbstractReceiver<Context, StandardProcessorResponse> {

    @Override
    public StandardProcessorResponse makeResponse() {
        return new StandardProcessorResponse();
    }
}
