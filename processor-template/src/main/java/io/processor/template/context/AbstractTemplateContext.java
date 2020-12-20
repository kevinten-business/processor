package io.processor.template.context;

import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.route.chain.RouteEventChain;
import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.key.RouteKey;
import io.processor.template.route.TemplateRouteKey;
import io.processor.template.route.type.CustomRouteType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 定义你的基础{@code Context}，并且需要继承{@link AbstractProcessorContext}。
 * {@code Processor}系统将会接收{@code Context}，并且使用{@code Context}的{@link #supplyContextRouteKey()}方法提供对应的{@link RouteKey}
 * 然后从{@code Processor}容器中基于{@link RouteKey}获取对应的{@code Processor}集合
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractTemplateContext extends AbstractProcessorContext<TemplateRouteKey> {

    /**
     * 基于你自己定义的路由类型来进行路由
     */
    private CustomRouteType customRouteType;

    /**
     * 强制每个Context都需要提供它对应的路由类型
     */
    public AbstractTemplateContext(CustomRouteType customRouteType) {
        this.customRouteType = customRouteType;
    }

    /**
     * 生成自定义的路由条件RouteKey。
     * {@code Context}与{@link RouteEvent}执行流程是两个维度，
     * 所以{@code Context}不需要指定它要路由的{@link RouteEvent}，
     * 关于{@link RouteEvent}的路由将由{@link RouteEventChain}提供
     */
    @Override
    public TemplateRouteKey supplyContextRouteKey() {
        return new TemplateRouteKey(this.getCustomRouteType());
    }
}
