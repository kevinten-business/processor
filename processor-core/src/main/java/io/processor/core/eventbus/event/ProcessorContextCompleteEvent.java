package io.processor.core.eventbus.event;

import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.context.AbstractProcessorContext;

/**
 * The Processor context complete event.
 */
public class ProcessorContextCompleteEvent extends AbstractProcessorEvent<AbstractProcessorContext, AbstractProcessorResponse> {

    /**
     * Instantiates a new Processor context complete event.
     *
     * @param source   the source
     * @param context  the context
     * @param response the response
     */
    public ProcessorContextCompleteEvent(Object source, AbstractProcessorContext context, AbstractProcessorResponse response) {
        super(source, context, response);
    }

    @Override
    public AbstractProcessorContext cloneRequest() {
        return this.getRequest();
    }

    @Override
    public AbstractProcessorResponse cloneResponse() {
        return this.getResponse();
    }

    @Override
    public AbstractProcessorEvent<AbstractProcessorContext, AbstractProcessorResponse> cloneEvent() {
        return new ProcessorContextCompleteEvent(this.source, this.cloneRequest(), this.cloneResponse());
    }
}
