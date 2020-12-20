package io.processor.core.route;

import com.kevinten.vrml.core.serialization.Serialization;
import com.google.common.collect.Lists;
import io.processor.core.processor.Processor;
import io.processor.core.route.annotation.adaptor.AbstractRouteAnnotationAdaptor;
import io.processor.core.route.annotation.adaptor.RouteAnnotationAdaptor;
import io.processor.core.route.chain.RouteEventChain;
import io.processor.core.route.context.ContextRoutable;
import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.interfacee.ProcessorRoutable;
import io.processor.core.route.key.AbstractRouteKey;
import io.processor.core.route.key.RouteKey;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * The Route holder. Maintained the relationship between all {@code RouteKey} and {@code Processor}.
 * <pre>
 *  +---------------+         ③        +---------------+    +---------------+    +---------------+
 *  |    context    |       ------>     |   processor1  | -> |   processor2  | -> |   processor3  |
 *  +---------------+                   +---------------+    +---------------+    +---------------+
 *      |         ↑                             |                    |                    |
 *  RouteKey  Set<Processor>                    |                   ①                    |
 *      ↓   ②    |                             ↓                    ↓                    ↓
 * +----------------------------------------------------------------------------------------+
 * |                                    Route Holder                                        |
 * |                            <RouteKey, Set<Processor>                                   |
 * |                            <RouteKey, Set<Processor>                                   |
 * |                            <RouteKey, Set<Processor>                                   |
 * +----------------------------------------------------------------------------------------+
 * </pre>
 * ①. All {@code Processor} register themselves to {@code RouteHolder} with key {@code RouteKey} and {@code RouteEvent}
 * ②. {@code Context} generate the {@code RouteKey}, and then get {@code Set<Processor>} from {@code RouteHolder}.
 * And Sort them in the order of {@code RouteEventChain}
 * ③. Processing the {@code Context} with {@code List<Processor>} chain sorted by {@code RouteEvent}.
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class RouteHolder implements ApplicationContextAware, InitializingBean {

    /**
     * The default capacity of all events.
     */
    private static final int DEFAULT_CAPACITY = 32;

    /**
     * The application context for getting beans with {@link ProcessorRoutable}.
     */
    private ApplicationContext applicationContext;

    /**
     * {@code Processor} chain sorted by {@code RouteEvent}.
     */
    private RouteEventChain<RouteKey<RouteEvent>, RouteEvent> routeEventChain;

    /**
     * {@code RouteEnable} annotation adaptor.
     */
    private RouteAnnotationAdaptor annotationAdaptor;

    /**
     * The map to store the pair of processor instance and route event name.
     * <pre>
     * {
     *     "processor" : "routeEvent"
     * }
     * </pre>
     */
    private static final Map<Processor, RouteEvent> PROCESSOR_ROUTE_EVENT_MAP = new ConcurrentHashMap<>(DEFAULT_CAPACITY);

    /**
     * The container to store (routeKey, routeEvent) and collection of processors.
     * <pre>
     *  {
     *      (routeKey, routeEvent) :[
     *              "processor1"
     *              "processor2"
     *              "processor3"
     *          ]
     *  }
     * </pre>
     */
    private static final Map<RouteKey, Set<Processor>> ROUTE_KEY_SET_MAP = new ConcurrentHashMap<>(DEFAULT_CAPACITY);

    /**
     * Gets all processors by {@code RouteKey} and {@code RouteEvent}.
     *
     * @param contextRoutable the context with routable
     * @return all processors sorted.
     */
    public List<Processor> getAllProcessors(ContextRoutable contextRoutable) {
        RouteKey contextRouteKey = contextRoutable.supplyContextRouteKey();

        List<RouteEvent> routeEventChain = this.routeEventChain.getRouteEventChain(contextRouteKey);

        List<Processor> processors = new ArrayList<>(DEFAULT_CAPACITY);
        for (RouteEvent routeEvent : routeEventChain) {
            contextRouteKey.setRouteEvent(routeEvent);

            Set<Processor> processorSet = ROUTE_KEY_SET_MAP.get(contextRouteKey);
            if (!CollectionUtils.isEmpty(processorSet)) {
                processors.addAll(processorSet);
            }
        }
        return processors;
    }

    public RouteEvent getProcessorEvent(Processor processor) {
        return PROCESSOR_ROUTE_EVENT_MAP.get(processor);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

        // autowire route event chain
        Try.run(() -> this.routeEventChain = applicationContext.getBean(RouteEventChain.class))
                .andFinally(() -> {
                    if (this.routeEventChain == null) {
                        log.error("[RouteHolder.setApplicationContext] RouteEventChain not defined!");
                        this.routeEventChain = new RouteEventChain<RouteKey<RouteEvent>, RouteEvent>() {
                            @Override
                            public List<RouteEvent> getRouteEventChain(RouteKey<RouteEvent> routeKey) {
                                return Lists.newArrayList(new RouteEvent() {
                                    @Override
                                    public String name() {
                                        return "default-event";
                                    }
                                });
                            }
                        };
                    }
                });

        // autowire route annotation adaptor
        Try.run(() -> this.annotationAdaptor = applicationContext.getBean(RouteAnnotationAdaptor.class))
                .andFinally(() -> {
                    if (this.annotationAdaptor == null) {
                        log.warn("[RouteHolder.setApplicationContext] RouteAnnotationAdaptor not defined!");
                        this.annotationAdaptor = new AbstractRouteAnnotationAdaptor<RouteKey, Annotation>() {
                            @Override
                            protected List supplyAnnotationRouteKeys(Annotation annotation) {
                                RouteKey routeKey = new AbstractRouteKey() {
                                    @Override
                                    public boolean equals(Object o) {
                                        if (this == o) {
                                            return true;
                                        }
                                        if (!(o instanceof AbstractRouteKey)) {
                                            return false;
                                        }
                                        AbstractRouteKey<?> that = (AbstractRouteKey<?>) o;
                                        return Objects.equals(routeEvent, that.getRouteEvent());
                                    }

                                    @Override
                                    public int hashCode() {
                                        return Objects.hash(routeEvent);
                                    }
                                };
                                return Collections.singletonList(routeKey);
                            }
                        };
                    }
                });
    }

    /**
     * Init processors to container
     */
    @Override
    public void afterPropertiesSet() {
        this.registerAnnotaion();
        this.registerInterface();
        this.monitorRouteProcessor();
    }

    private void registerAnnotaion() {
        Class annotationType = this.annotationAdaptor.type();
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(annotationType);
        for (String beanName : beanNames) {
            this.registerAnnotationBean(beanName);
        }
    }

    private void registerInterface() {
        String[] beanNames = applicationContext.getBeanNamesForType(ProcessorRoutable.class);
        for (String beanName : beanNames) {
            this.registerInterfaceBean(beanName);
        }
    }

    /**
     * Init annotation bean to container
     */
    private void registerAnnotationBean(String beanName) {
        Class type = this.annotationAdaptor.type();
        Class<?> beanType = applicationContext.getType(beanName);
        Annotation annotation = beanType.getAnnotation(type);
        Processor processor;
        try {
            // {@code RouteEnable} must impl by {@code Processor}
            processor = applicationContext.getBean(beanName, Processor.class);
        } catch (Exception e) {
            log.error("[RouteHolder.registerAnnotationBean] {}.class must impl by Processor.class", annotation.getClass().getSimpleName(), e);
            return;
        }
        List<RouteKey> routeKeys = this.annotationAdaptor.trans(annotation);
        this.registerProcessor(routeKeys, processor);
    }

    /**
     * Init interface bean to container
     */
    private void registerInterfaceBean(String beanName) {
        Processor processor;
        try {
            // {@code ProcessorRoutable} must impl by {@code Processor}
            processor = applicationContext.getBean(beanName, Processor.class);
        } catch (Exception e) {
            log.error("[RouteHolder.registerInterfaceBean] ProcessorRoutable.class must impl by Processor.class", e);
            return;
        }
        ProcessorRoutable processorRoutable = applicationContext.getBean(beanName, ProcessorRoutable.class);
        List<RouteKey> routeKeys = processorRoutable.supplyProcessorRouteKeys();
        this.registerProcessor(routeKeys, processor);
    }

    /**
     * Init processor to container
     */
    private void registerProcessor(List<RouteKey> routeKeys, Processor processor) {
        if (!CollectionUtils.isEmpty(routeKeys)) {
            for (RouteKey routeKey : routeKeys) {
                this.registerProcessorWithEvent(processor, routeKey);
                this.registerProcessorWithKey(routeKey, processor);
            }
        }
    }

    /**
     * Init processor to container with event({@code RouteEvent})
     */
    private void registerProcessorWithEvent(Processor processor, RouteKey routeKey) {
        RouteEvent event = routeKey.getRouteEvent();
        PROCESSOR_ROUTE_EVENT_MAP.put(processor, event);
        routeKey.setRouteEvent(event);
    }

    /**
     * Init processor to container with key({@code RouteKey})
     */
    private void registerProcessorWithKey(RouteKey routeKey, Processor processor) {
        ROUTE_KEY_SET_MAP.computeIfAbsent(routeKey, k -> new HashSet<>(4)).add(processor);
    }

    private void monitorRouteProcessor() {
        Map<String, List<String>> collect = ROUTE_KEY_SET_MAP.entrySet().stream()
                .map(routeKeySetEntry -> {
                    Map.Entry<String, List<String>> entry = Pair.of(
                            Serialization.GSON.toJson(routeKeySetEntry.getKey()),
                            routeKeySetEntry.getValue().stream()
                                    .map(processor -> processor.getClass().getSimpleName())
                                    .collect(Collectors.toList()));
                    return entry;
                })
                .collect(Collectors.toMap(
                        routeKeyListEntry -> routeKeyListEntry.getKey(),
                        routeKeyListEntry -> routeKeyListEntry.getValue()));
        collect.entrySet().forEach(entry -> {
            log.info("[ProcessorRouteTable] [<{},{}>]", Serialization.GSON.toJson(entry.getKey()), Serialization.GSON.toJson(entry.getValue()));
        });
    }
}
