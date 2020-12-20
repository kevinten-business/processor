package io.processor.core.eventbus.publisher;

import com.kevinten.vrml.eventbus.publisher.AbstractAsyncEventPublisher;
import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.eventbus.event.AbstractProcessorEvent;
import io.processor.core.response.AbstractProcessorResponse;
import org.springframework.stereotype.Component;

/**
 * The Async processor context event publisher.
 */
@Component
public class AsyncProcessorContextEventPublisher extends AbstractAsyncEventPublisher<AbstractProcessorEvent<AbstractProcessorContext, AbstractProcessorResponse>> {
}
