package io.processor.application.processor.processor.flight;

import com.kevinten.vrml.core.serialization.Serialization;
import io.processor.application.processor.context.FlightContext;
import io.processor.application.processor.error.code.AppErrorCodes;
import io.processor.application.processor.error.exception.AppErrorCodeException;
import io.processor.application.processor.route.AppEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

/**
 * Flight Filter Processor
 */
@Slf4j
@Component
public class FlightFilterInterfaceProcessor extends AbstractAppFlightInterfaceProcessor {

    @Override
    protected void doProcess(FlightContext context) throws EventException {
        log.info("[FlightFilterProcessor.doProcess] context[{}]", Serialization.GSON.toJson(context));
        if (!"Shanghai".equals(context.getFlightCity())) {
            throw new AppErrorCodeException(AppErrorCodes.PARAMETER_FLIGHT_CITY_ERROR);
        }
    }

    /**
     * 基于Event进行路由
     */
    @Override
    protected AppEventType routeEvent() {
        return AppEventType.FILTER;
    }
}
