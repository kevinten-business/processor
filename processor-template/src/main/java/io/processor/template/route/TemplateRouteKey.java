package io.processor.template.route;

import io.processor.core.context.ProcessorContext;
import io.processor.core.route.chain.RouteEventChain;
import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.key.AbstractRouteKey;
import io.processor.template.route.type.CustomRouteType;

import java.util.Objects;

/**
 * 自定义路由用来匹配的类型，需要继承{@link AbstractRouteKey}
 * 默认只根据{@link TemplateRouteEvent}来进行路由选择，但你可以添加任意的自定义字段来进行额外的路由。
 * 例如，以下添加了一个额外的枚举字段{@code CustomRouteType}
 */
public class TemplateRouteKey extends AbstractRouteKey<TemplateRouteEvent> {

    private final CustomRouteType customRouteType;

    /**
     * 当你定义了一个自定义的路由值，你总是希望它生效的是吧？
     * 可以使用构造函数强制{@link ProcessorContext}和{@link io.processor.core.processor.Processor}
     * 都必须对该路由类型赋值，避免出现为空的情况
     */
    public TemplateRouteKey(CustomRouteType customRouteType) {
        this.customRouteType = customRouteType;
    }

    /**
     * 当不同的自定义类型，可能有不同的{@link RouteEvent}链路顺序的时候。
     * 提供一个{@code get}方法，并在自定义的{@link RouteEventChain}中基于不同类型，返回不同的EVENT处理链路顺序。
     */
    public CustomRouteType getCustomRouteType() {
        return customRouteType;
    }

    /*
     * 因为{@code RouteKey}将会被用作{@code Map}的key值，所以需要重写{@code equals}方法。
     * 注意，请包含全部的路由值，如果漏掉了，可能导致无法精确匹配
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemplateRouteKey)) return false;
        TemplateRouteKey that = (TemplateRouteKey) o;
        return customRouteType == that.customRouteType
                // 不要漏掉父类中默认的RouteEvent值！
                && routeEvent == that.routeEvent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customRouteType, routeEvent);
    }
}
