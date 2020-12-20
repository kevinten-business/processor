package io.processor.application.processor.route;

import io.processor.core.route.key.AbstractRouteKey;

import java.util.Objects;

/**
 * 自定义的路由KEY
 */
public class AppRouteKey extends AbstractRouteKey<AppEventType> {

    /**
     * 基于Scheme进行路由
     */
    private final AppSchemeType appSchemeType;

    public AppRouteKey(AppSchemeType appSchemeType) {
        super();
        this.appSchemeType = appSchemeType;
    }

    public AppRouteKey(AppSchemeType appSchemeType, AppEventType appEventType) {
        super(appEventType);
        this.appSchemeType = appSchemeType;
    }

    public AppSchemeType getAppSchemeType() {
        return appSchemeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppRouteKey)) {
            return false;
        }
        AppRouteKey that = (AppRouteKey) o;
        return appSchemeType == that.appSchemeType
                && routeEvent == that.routeEvent;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appSchemeType, routeEvent);
    }
}
