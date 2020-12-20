package io.processor.core.route.annotation.adaptor;

import io.processor.core.route.annotation.RouteEnable;
import io.processor.core.route.key.RouteKey;

import java.lang.annotation.Annotation;
import java.util.List;

public interface RouteAnnotationAdaptor<Key extends RouteKey, A extends Annotation> {

    List<Key> trans(A routeEnable);

    Class<A> type();
}
