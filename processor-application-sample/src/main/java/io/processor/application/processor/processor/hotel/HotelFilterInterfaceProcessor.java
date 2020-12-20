package io.processor.application.processor.processor.hotel;

import com.kevinten.vrml.core.serialization.Serialization;
import io.processor.application.processor.context.HotelContext;
import io.processor.application.processor.error.code.AppErrorCodes;
import io.processor.application.processor.error.exception.AppErrorCodeException;
import io.processor.application.processor.route.AppEventType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.w3c.dom.events.EventException;

/**
 * Hotel Filter Processor
 */
@Slf4j
@Component
public class HotelFilterInterfaceProcessor extends AbstractAppHotelInterfaceProcessor {

    @Override
    protected void doProcess(HotelContext context) throws EventException {
        log.info("[HotelFilterProcessor.doProcess] context[{}]", Serialization.GSON.toJson(context));
        if (!"Halton".equals(context.getHotelName())) {
            throw new AppErrorCodeException(AppErrorCodes.PARAMETER_HOTEL_NAME_ERROR);
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
