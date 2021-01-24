package io.processor.core.route.annotation.adaptor;

import io.processor.core.route.key.RouteKey;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

/**
 * The Abstract route annotation adaptor.
 *
 * @param <Key> the type RouteKey
 * @param <A>   the type Annotation
 */
public abstract class AbstractRouteAnnotationAdaptor<Key extends RouteKey, A extends Annotation>
        implements RouteAnnotationAdaptor<Key, A> {

    @Override
    public Class<A> type() {
        return (Class<A>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public List<Key> trans(A annotation) {
        if (annotation != null) {
            return this.supplyAnnotationRouteKeys(annotation);
        }
        return Collections.emptyList();
    }

    protected abstract List<Key> supplyAnnotationRouteKeys(A annotation);
}
