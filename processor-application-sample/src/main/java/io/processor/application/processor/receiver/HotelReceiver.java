package io.processor.application.processor.receiver;

import io.processor.application.processor.context.HotelContext;
import io.processor.application.entity.HotelRequest;
import io.processor.core.receiver.AbstractStandardReceiver;
import org.springframework.stereotype.Component;

/**
 * Hotel链路接收器
 */
@Component
public class HotelReceiver extends AbstractStandardReceiver<HotelContext> {

    public HotelContext generateContext(HotelRequest hotelRequest){
        HotelContext hotelContext = new HotelContext();
        hotelContext.setHotelId(hotelRequest.getHotelId());
        hotelContext.setHotelName(hotelRequest.getHotelName());
        return hotelContext;
    }
}
