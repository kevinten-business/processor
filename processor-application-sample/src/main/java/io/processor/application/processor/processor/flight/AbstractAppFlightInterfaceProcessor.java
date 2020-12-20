package io.processor.application.processor.processor.flight;

import io.processor.application.processor.context.FlightContext;
import io.processor.application.processor.processor.AbstractAppInterfaceProcessor;
import io.processor.application.processor.route.AppSchemeType;

/**
 * Flight Processor
 */
public abstract class AbstractAppFlightInterfaceProcessor extends AbstractAppInterfaceProcessor<FlightContext> {

    /**
     * 基于Scheme进行路由
     */
    @Override
    protected AppSchemeType routeScheme() {
        return AppSchemeType.FLIGHT;
    }
}
