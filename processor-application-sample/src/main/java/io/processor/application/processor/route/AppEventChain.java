package io.processor.application.processor.route;

import io.processor.core.route.chain.RouteEventChain;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 自定义的Event路由链路
 */
@Component
public class AppEventChain implements RouteEventChain<AppRouteKey, AppEventType> {

    /**
     * 基于Scheme进行路由
     *
     * @return Event链路
     */
    @Override
    public List<AppEventType> getRouteEventChain(AppRouteKey routeKey) {
        if (AppSchemeType.FLIGHT.equals(routeKey.getAppSchemeType())) {
            List<AppEventType> appEventTypes = new ArrayList<>();
            appEventTypes.add(AppEventType.FILTER);
            appEventTypes.add(AppEventType.SUPPLEMENT);
            appEventTypes.add(AppEventType.SEND);
            return appEventTypes;
        }
        if (AppSchemeType.HOTEL.equals(routeKey.getAppSchemeType())) {
            List<AppEventType> appEventTypes = new ArrayList<>();
            appEventTypes.add(AppEventType.FILTER);
            appEventTypes.add(AppEventType.SUPPLEMENT);
            appEventTypes.add(AppEventType.SEND);
            return appEventTypes;
        }
        return Collections.singletonList(AppEventType.SEND);
    }
}
