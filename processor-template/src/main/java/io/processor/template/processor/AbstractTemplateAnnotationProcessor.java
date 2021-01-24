package io.processor.template.processor;

import io.processor.core.processor.AbstractProcessor;
import io.processor.core.processor.annotation.AbstractStandardAnnotationProcessor;
import io.processor.core.processor.interfacee.AbstractStandardInterfaceProcessor;
import io.processor.template.context.AbstractTemplateContext;
import io.processor.template.route.TemplateRouteKey;

/**
 * 定义{@code Processor}基础链路处理器，将会绑定一个具体的{@code Context}类型，需要继承{@link AbstractProcessor}。
 * 当然，也可以使用标准的{@link AbstractStandardInterfaceProcessor}，它将一些抽象方法封装为标准实现，如果能满足则可以直接使用。
 * 如果你有更多的自定义需要，可以继承自{@link AbstractProcessor}
 */
public abstract class AbstractTemplateAnnotationProcessor<Context extends AbstractTemplateContext>
        extends AbstractStandardAnnotationProcessor<Context, TemplateRouteKey> {
}
