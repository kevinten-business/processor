package io.processor.application.processor.context;

import com.kevinten.vrml.metric.Metric;
import io.processor.application.processor.route.AppSchemeType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Hotel Context
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Metric
public class HotelContext extends AbstractApplicationContext {

    private String hotelId;
    private String hotelName;

    public HotelContext() {
        super(AppSchemeType.HOTEL);
    }
}
