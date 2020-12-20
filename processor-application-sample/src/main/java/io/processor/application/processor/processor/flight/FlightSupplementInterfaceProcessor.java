package io.processor.application.processor.processor.flight;

import com.kevinten.vrml.core.serialization.Serialization;
import io.processor.application.processor.context.FlightContext;
import io.processor.application.processor.route.AppEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

/**
 * Flight Supplement Processor
 */
@Slf4j
@Component
public class FlightSupplementInterfaceProcessor extends AbstractAppFlightInterfaceProcessor {

    @Override
    protected void doProcess(FlightContext context) throws EventException {
        log.info("[FlightSupplementProcessor.doProcess] context[{}]", Serialization.GSON.toJson(context));
    }

    /**
     * 基于Event进行路由
     */
    @Override
    protected AppEventType routeEvent() {
        return AppEventType.SUPPLEMENT;
    }
}
