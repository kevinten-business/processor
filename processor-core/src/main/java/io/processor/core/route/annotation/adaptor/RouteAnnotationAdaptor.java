package io.processor.core.route.annotation.adaptor;

import io.processor.core.route.key.RouteKey;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * The Route annotation adaptor.
 *
 * @param <Key> the type RouteKey
 * @param <A>   the type Annotation
 */
public interface RouteAnnotationAdaptor<Key extends RouteKey, A extends Annotation> {

    /**
     * Trans list.
     *
     * @param routeEnable the route enable
     * @return the list
     */
    List<Key> trans(A routeEnable);

    /**
     * Type class.
     *
     * @return the class
     */
    Class<A> type();
}
