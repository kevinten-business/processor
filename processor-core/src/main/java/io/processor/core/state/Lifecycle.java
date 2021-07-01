package io.processor.core.state;

/**
 * The Lifecycle node.
 */
public interface Lifecycle {

    /**
     * Init. Do once.
     */
    void init();

    /**
     * Destroy. Do once.
     */
    void destroy();
}
