package io.processor.application.processor.context;

import com.kevinten.vrml.metric.Metric;
import io.processor.application.processor.route.AppSchemeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Flight Context
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Metric
public class FlightContext extends AbstractApplicationContext {

    private String flightId;
    private String flightCity;

    public FlightContext() {
        super(AppSchemeType.FLIGHT);
    }
}
