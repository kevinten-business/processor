package io.processor.core.state;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;

/**
 * The {@link Lifecycle} manager. Using Spring framework.
 */
@Component
public final class LifecycleExecutor {

    @Autowired
    private List<Lifecycle> lifecycleList;

    /**
     * Do {@link Lifecycle#init()}
     */
    @PostConstruct
    public void init() {
        if (!CollectionUtils.isEmpty(lifecycleList)) {
            lifecycleList.parallelStream()
                    .forEach(Lifecycle::init);
        }
    }

    /**
     * Do {@link Lifecycle#destroy()}
     */
    @PreDestroy
    public void destroy() {
        if (!CollectionUtils.isEmpty(lifecycleList)) {
            lifecycleList.parallelStream()
                    .forEach(Lifecycle::destroy);
        }
    }
}
