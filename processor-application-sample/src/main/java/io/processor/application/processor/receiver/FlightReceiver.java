package io.processor.application.processor.receiver;

import io.processor.application.processor.context.FlightContext;
import io.processor.application.entity.FlightRequest;
import io.processor.core.receiver.AbstractStandardReceiver;
import org.springframework.stereotype.Component;

/**
 * Flight链路接收器
 */
@Component
public class FlightReceiver extends AbstractStandardReceiver<FlightContext> {

    public FlightContext generateContext(FlightRequest flightRequest) {
        FlightContext flightContext = new FlightContext();
        flightContext.setFlightId(flightRequest.getFlightId());
        flightContext.setFlightCity(flightRequest.getFlightCity());
        return flightContext;
    }
}
