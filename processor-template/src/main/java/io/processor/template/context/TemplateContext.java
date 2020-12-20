package io.processor.template.context;

import com.kevinten.vrml.metric.Metric;
import io.processor.template.route.type.CustomRouteType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义的各种类型的{@code Context}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Metric
public class TemplateContext extends AbstractTemplateContext {

    private String id;

    public TemplateContext() {
        super(CustomRouteType.STUDENT);
    }
}
