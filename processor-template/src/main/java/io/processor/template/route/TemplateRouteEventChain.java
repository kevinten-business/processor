package io.processor.template.route;

import io.processor.core.processor.Processor;
import io.processor.core.route.chain.RouteEventChain;
import io.processor.core.route.event.RouteEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 不同的路由类型可能有不同的{@link RouteEvent}处理链路，同时处理链路的顺序也需要灵活的定义。
 * 定义你的{@code RouteEventChain}，需要实现{@link RouteEventChain}。
 * 请注意需要将其注册为{@code Spring}的Bean，可以使用{@link Component}注解
 */
@Component
public class TemplateRouteEventChain implements RouteEventChain<TemplateRouteKey, TemplateRouteEvent> {

    /**
     * 在这里，提供你的{@link RouteEvent}处理链路吧。
     * 基于你自己定义的{@link io.processor.core.route.key.RouteKey}，你可以使用任意的路由手段，来返回对应处理链路的所有事件顺序。
     * 再之后，这条路由链路上的{@link Processor}们，将会按照返回的{@link RouteEvent}顺序，进行责任链的执行
     */
    @Override
    public List<TemplateRouteEvent> getRouteEventChain(TemplateRouteKey routeKey) {
        // 你可以使用动态配置的手段，从而灵活的调整你的处理链路
        List<TemplateRouteEvent> routeEvents = new ArrayList<>();
        routeEvents.add(TemplateRouteEvent.STEP_1);
        routeEvents.add(TemplateRouteEvent.STEP_2);
        return routeEvents;
    }
}
