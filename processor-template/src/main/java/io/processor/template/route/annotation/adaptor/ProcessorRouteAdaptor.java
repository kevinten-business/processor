package io.processor.template.route.annotation.adaptor;

import io.processor.core.route.annotation.adaptor.AbstractRouteAnnotationAdaptor;
import io.processor.template.route.TemplateRouteEvent;
import io.processor.template.route.TemplateRouteKey;
import io.processor.template.route.annotation.ProcessorRoute;
import io.processor.template.route.type.CustomRouteType;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 当你使用自定义路由注解的方式进行{@code Processor}路由时。
 * Processor核心模块并不知晓您的具体类型，以及该如何将注解映射到{@code RouteKey}上去。
 * 所以，你需要实现{@link AbstractRouteAnnotationAdaptor}，从而提供自定义注解和{@code RouteKey}的映射关系。
 * 将其注册到Spring上下文中，Processor核心模块将会通过依赖注入使用它
 */
@Component
public class ProcessorRouteAdaptor extends AbstractRouteAnnotationAdaptor<TemplateRouteKey, ProcessorRoute> {

    /**
     * 定义自定义注解和{@code RouteKey}之间的映射关系
     */
    @Override
    protected List<TemplateRouteKey> supplyAnnotationRouteKeys(ProcessorRoute annotation) {
        CustomRouteType customRouteType = annotation.customRouteType();
        TemplateRouteEvent templateRouteEvent = annotation.templateRouteEvent();
        TemplateRouteKey templateRouteKey = new TemplateRouteKey(customRouteType);
        templateRouteKey.setRouteEvent(templateRouteEvent);
        return Collections.singletonList(templateRouteKey);
    }
}
