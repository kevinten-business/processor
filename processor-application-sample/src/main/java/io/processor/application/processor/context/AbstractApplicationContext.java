package io.processor.application.processor.context;

import io.processor.application.processor.route.AppRouteKey;
import io.processor.application.processor.route.AppSchemeType;
import io.processor.core.context.AbstractProcessorContext;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * App基础Context
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class AbstractApplicationContext extends AbstractProcessorContext<AppRouteKey> {

    /**
     * 基于Scheme进行路由
     */
    private AppSchemeType appSchemeType;

    public AbstractApplicationContext(AppSchemeType appSchemeType) {
        this.appSchemeType = appSchemeType;
    }

    /**
     * 生成自定义的路由条件RouteKey
     */
    @Override
    public AppRouteKey supplyContextRouteKey() {
        return new AppRouteKey(this.getAppSchemeType());
    }
}
