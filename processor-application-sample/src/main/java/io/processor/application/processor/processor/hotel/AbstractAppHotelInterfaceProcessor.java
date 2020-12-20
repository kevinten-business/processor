package io.processor.application.processor.processor.hotel;

import io.processor.application.processor.context.HotelContext;
import io.processor.application.processor.processor.AbstractAppInterfaceProcessor;
import io.processor.application.processor.route.AppSchemeType;

/**
 * Hotel Processor
 */
public abstract class AbstractAppHotelInterfaceProcessor extends AbstractAppInterfaceProcessor<HotelContext> {

    /**
     * 基于Scheme进行路由
     */
    @Override
    protected AppSchemeType routeScheme() {
        return AppSchemeType.HOTEL;
    }
}
