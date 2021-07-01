package io.processor.core.processor;

import io.processor.core.context.ProcessorContext;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.state.Recover;

public interface RecoverProcessor<Context extends ProcessorContext, Response extends AbstractProcessorResponse>
        extends Processor<Context, Response>, Recover<Context> {

    @Override
    Response process(Context context);

    @Override
    void recovery(Context context);
}
