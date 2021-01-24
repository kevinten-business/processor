package io.processor.template.processor;

import com.kevinten.processor.common.error.exception.ProcessorErrorCodeException;
import io.processor.core.route.event.RouteEvent;
import io.processor.template.context.TemplateContext;
import io.processor.template.error.code.TemplateErrorCodes;
import io.processor.template.error.exception.TemplateErrorCodeException;
import io.processor.template.route.TemplateRouteEvent;
import io.processor.template.route.type.CustomRouteType;
import org.springframework.stereotype.Component;

/**
 * 自定义的各种类型的{@code Processor}，需要注册到{@code Spring}环境当中
 */
@Component
public class TemplateInterfaceProcessor extends AbstractTemplateInterfaceProcessor<TemplateContext> {

    /**
     * 这个{@code Processor}对应的自定义路由类型为{@link CustomRouteType#STUDENT}
     */
    @Override
    protected CustomRouteType customRouteType() {
        return CustomRouteType.STUDENT;
    }

    /**
     * 这个{@code Processor}对应的处理链路步骤为{@link TemplateRouteEvent#STEP_1}
     */
    @Override
    protected RouteEvent routeEvent() {
        return TemplateRouteEvent.STEP_1;
    }

    @Override
    protected void doProcess(TemplateContext context) throws ProcessorErrorCodeException {
        // 业务逻辑

        // 通过异常来终结处理链路流程
        // 使用自定义的异常集，例如{@code TemplateErrorCodeException}来维护链路中的信息
        // 使用自定义的错误码，例如{@code TemplateErrorCodes}来确保错误语义
        throw new TemplateErrorCodeException(TemplateErrorCodes.BUSINESS_ERROR);
    }
}
