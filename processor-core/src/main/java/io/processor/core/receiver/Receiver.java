package io.processor.core.receiver;


import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.context.ProcessorContext;

/**
 * This module is to receive a context, and than dispatch it to different process chain to handle.
 *
 * @param <Context>  the context type.
 * @param <Response> the response type.
 */
@FunctionalInterface
public interface Receiver<Context extends ProcessorContext, Response extends AbstractProcessorResponse> {

    /**
     * Receive a context and then dispatching.
     *
     * @param context the context to receive.
     * @return the response after processing.
     */
    Response receive(Context context);
}
