package io.processor.application.processor.error.exception;

import com.kevinten.vrml.error.code.ErrorCodeContext;
import com.kevinten.vrml.error.exception.ErrorCodeException;
import com.kevinten.processor.common.error.code.ProcessorErrorCodeContext;

/**
 * The Processor error code exception.
 */
public class AppErrorCodeException extends ErrorCodeException {

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param processorErrorCodeContext the processor error code context
     */
    public AppErrorCodeException(ProcessorErrorCodeContext processorErrorCodeContext) {
        super(processorErrorCodeContext);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     */
    public AppErrorCodeException(ErrorCodeContext errorCodeContext) {
        super(errorCodeContext);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param cause            the cause
     */
    public AppErrorCodeException(ErrorCodeContext errorCodeContext, Throwable cause) {
        super(errorCodeContext, cause);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param errorMessage     the error message
     */
    public AppErrorCodeException(ErrorCodeContext errorCodeContext, String errorMessage) {
        super(errorCodeContext, errorMessage);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param errorMessage     the error message
     * @param cause            the cause
     */
    public AppErrorCodeException(ErrorCodeContext errorCodeContext, String errorMessage, Throwable cause) {
        super(errorCodeContext, errorMessage, cause);
    }
}
