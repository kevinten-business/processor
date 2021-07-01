package io.processor.core.state;

/**
 * The Recover ability.
 *
 * @param <T> the data
 */
public interface Recover<T> {

    /**
     * Do Recovery.
     *
     * @param context the context
     */
    void recovery(T context);
}
