package io.processor.template.receiver;

import io.processor.core.receiver.AbstractReceiver;
import io.processor.core.receiver.AbstractStandardReceiver;
import io.processor.template.context.TemplateContext;
import org.springframework.stereotype.Component;

/**
 * 定义{@code Processor}链路接收器，将会绑定一个具体的{@code Context}类型，需要继承{@link AbstractReceiver}。
 * 当然，也可以使用标准的{@link AbstractStandardReceiver}，它将一些抽象方法封装为标准实现，如果能满足则可以直接使用。
 * 如果你有更多的自定义需要，可以继承自{@link AbstractReceiver}
 */
@Component
public class TemplateReceiver extends AbstractStandardReceiver<TemplateContext> {
}
