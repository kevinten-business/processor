package io.processor.template.processor;

import io.processor.core.processor.AbstractProcessor;
import io.processor.core.processor.interfacee.AbstractStandardInterfaceProcessor;
import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.key.RouteKey;
import io.processor.template.context.AbstractTemplateContext;
import io.processor.template.route.TemplateRouteEvent;
import io.processor.template.route.TemplateRouteKey;
import io.processor.template.route.type.CustomRouteType;

/**
 * 定义{@code Processor}基础链路处理器，将会绑定一个具体的{@code Context}类型，需要继承{@link AbstractProcessor}。
 * 当然，也可以使用标准的{@link AbstractStandardInterfaceProcessor}，它将一些抽象方法封装为标准实现，如果能满足则可以直接使用。
 * 如果你有更多的自定义需要，可以继承自{@link AbstractProcessor}
 */
public abstract class AbstractTemplateInterfaceProcessor<Context extends AbstractTemplateContext>
        extends AbstractStandardInterfaceProcessor<Context, TemplateRouteKey> {

    /**
     * {@code Processor}的{@link RouteKey}生成规则：
     * 首先传入自定义的路由类型值到{@link RouteKey}构造函数当中，以确保静态的路由规则匹配上。
     * 然后通过{@link RouteKey#setRouteEvent(RouteEvent)}将{@code Processor}对应的处理链路的{@code Event}值传入
     * 最终生成完整的路由匹配KEY
     */
    @Override
    protected TemplateRouteKey supplyRouteKey() {
        TemplateRouteKey templateRouteKey = new TemplateRouteKey(this.customRouteType());
        templateRouteKey.setRouteEvent((TemplateRouteEvent) this.routeEvent());
        return templateRouteKey;
    }

    /**
     * 基于自定义的类型值来进行路由
     */
    protected abstract CustomRouteType customRouteType();
}
