package io.processor.template.processor;

import com.kevinten.processor.common.error.exception.ProcessorErrorCodeException;
import io.processor.core.route.annotation.RouteEnable;
import io.processor.template.context.TemplateContext;
import io.processor.template.error.code.TemplateErrorCodes;
import io.processor.template.error.exception.TemplateErrorCodeException;
import io.processor.template.route.TemplateRouteEvent;
import io.processor.template.route.annotation.ProcessorRoute;
import io.processor.template.route.type.CustomRouteType;

/**
 * 自定义的各种类型的{@code Processor}，需要注册到{@code Spring}环境当中
 */
@RouteEnable
@ProcessorRoute(customRouteType = CustomRouteType.TEACHER, templateRouteEvent = TemplateRouteEvent.STEP_2)
public class TemplateAnnotationProcessor extends AbstractTemplateAnnotationProcessor<TemplateContext> {

    @Override
    protected void doProcess(TemplateContext context) throws ProcessorErrorCodeException {
        // 业务逻辑

        // 通过异常来终结处理链路流程
        // 使用自定义的异常集，例如{@code TemplateErrorCodeException}来维护链路中的信息
        // 使用自定义的错误码，例如{@code TemplateErrorCodes}来确保错误语义
        throw new TemplateErrorCodeException(TemplateErrorCodes.BUSINESS_ERROR);
    }
}
