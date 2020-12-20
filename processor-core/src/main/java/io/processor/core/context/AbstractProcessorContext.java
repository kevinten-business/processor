package io.processor.core.context;

import com.kevinten.vrml.metric.Metric;
import io.processor.core.route.event.RouteEvent;
import io.processor.core.route.key.RouteKey;
import lombok.Data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The Abstract processor context.
 *
 * @param <Route> the route key type.
 */
@Data
public abstract class AbstractProcessorContext<Route extends RouteKey> implements ProcessorContext<Route> {

    /*
     * Process properties
     */

    private String errorCode;
    private String messageId;

    /*
     * Execute properties
     */

    private long startTime;
    private long endTime;

    /*
     * Route properties
     */

    private RouteEvent previousRouteEvent;
    private RouteEvent routeEvent;

    /*
     * Tracing properties
     */

    /**
     * The tracing data map of context data.
     */
    @Metric(isIndex = false)
    private Map<String, String> traceMap;

    /**
     * Initial {@link #traceMap}
     */
    private void initTrace() {
        if (this.traceMap == null) {
            synchronized (this) {
                if (this.traceMap == null) {
                    this.traceMap = new ConcurrentHashMap<>();
                }
            }
        }
    }

    /**
     * Returns trace data in the map.
     *
     * @return trace data map
     */
    @Override
    public Map<String, String> getTraceMap() {
        this.initTrace();
        return this.traceMap;
    }

    @Override
    public void setTraceMap(Map<String, String> traceMap) {
        this.traceMap = traceMap;
    }

    /**
     * Add {@code key,value} to the trace data map.
     *
     * @param key   key
     * @param value value
     */
    @Override
    public void addTrace(String key, String value) {
        if (key != null) {
            this.getTraceMap().put(key, value);
        }
    }
}
