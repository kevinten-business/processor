package io.processor.core.processor;

import io.processor.core.context.ProcessorContext;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.state.Lifecycle;

public interface LifecycleProcessor<Context extends ProcessorContext, Response extends AbstractProcessorResponse>
        extends Processor<Context, Response>, Lifecycle {

    @Override
    Response process(Context context);

    @Override
    void init();

    @Override
    void destroy();
}
