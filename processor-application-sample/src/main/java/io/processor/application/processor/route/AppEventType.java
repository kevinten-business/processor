package io.processor.application.processor.route;

import io.processor.core.route.event.RouteEvent;

/**
 * 自定义的路由枚举类型
 */
public enum AppEventType implements RouteEvent {

    /**
     * 基于Event进行路由
     */
    FILTER,
    SUPPLEMENT,
    SEND,
    ;
}
