package io.processor.core.eventbus.event;

import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.context.AbstractProcessorContext;

/**
 * The Processor context step event.
 */
public class ProcessorContextStepEvent extends AbstractProcessorEvent<AbstractProcessorContext, AbstractProcessorResponse> {

    /**
     * Instantiates a new Processor context step event.
     *
     * @param source   the source
     * @param context  the context
     * @param response the response
     */
    public ProcessorContextStepEvent(Object source, AbstractProcessorContext context, AbstractProcessorResponse response) {
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
        return new ProcessorContextStepEvent(this.source, this.cloneRequest(), this.cloneResponse());
    }
}
