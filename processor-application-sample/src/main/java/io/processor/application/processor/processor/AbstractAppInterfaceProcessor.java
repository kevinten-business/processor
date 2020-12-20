package io.processor.application.processor.processor;

import io.processor.application.processor.context.AbstractApplicationContext;
import io.processor.application.processor.route.AppEventType;
import io.processor.application.processor.route.AppRouteKey;
import io.processor.application.processor.route.AppSchemeType;
import io.processor.core.processor.interfacee.AbstractStandardInterfaceProcessor;

/**
 * 基础Processor
 */
public abstract class AbstractAppInterfaceProcessor<Context extends AbstractApplicationContext>
        extends AbstractStandardInterfaceProcessor<Context, AppRouteKey> {

    @Override
    protected AppRouteKey supplyRouteKey() {
        return new AppRouteKey(this.routeScheme(), (AppEventType) this.routeEvent());
    }

    /**
     * 基于Scheme进行路由
     */
    protected abstract AppSchemeType routeScheme();
}
