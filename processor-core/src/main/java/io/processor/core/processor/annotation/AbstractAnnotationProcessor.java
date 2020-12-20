package io.processor.core.processor.annotation;

import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.processor.AbstractProcessor;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.route.key.RouteKey;

public abstract class AbstractAnnotationProcessor<Context extends AbstractProcessorContext, Response extends AbstractProcessorResponse, Route extends RouteKey>
        extends AbstractProcessor<Context, Response, Route> {
}
