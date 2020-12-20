package io.processor.template.route;

import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.chain.RouteEventChain;

/**
 * 定义一个枚举类型，并且实现{@link RouteEvent}。
 * 该类型将作为{@code Processor}处理链路的步骤顺序。
 */
public enum TemplateRouteEvent implements RouteEvent {

    /**
     * 对你的系统划分步骤，并通过{@link RouteEventChain}来决定它们的顺序
     */
    STEP_1,
    STEP_2,
    ;
}
