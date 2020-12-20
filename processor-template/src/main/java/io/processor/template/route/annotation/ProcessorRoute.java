package io.processor.template.route.annotation;

import io.processor.core.route.annotation.RouteEnable;
import io.processor.template.route.TemplateRouteEvent;
import io.processor.template.route.type.CustomRouteType;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义{@code Processor}基础路由注解，使用{@link RouteEnable}修饰自定义注解，
 * 同时不要忘记{@code Target}{@code Retention}和{@code Component}（因为它们在嵌套注解中不生效！）。
 * 在自定义注解中，随意使用你的路由值，将会在完全匹配的时候，获取到指定的{@code Processor}集合
 * 之后，将{@link ProcessorRoute}自定义注解修饰到你的{@code Processor}实现上吧。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
@RouteEnable
public @interface ProcessorRoute {

    /**
     * 自定义路由值
     */
    CustomRouteType customRouteType();

    /**
     * 自定义路由值
     */
    TemplateRouteEvent templateRouteEvent();
}
