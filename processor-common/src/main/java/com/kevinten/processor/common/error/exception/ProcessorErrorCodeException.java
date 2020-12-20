package com.kevinten.processor.common.error.exception;

import com.kevinten.processor.common.error.code.ProcessorErrorCodeContext;
import com.kevinten.vrml.error.code.ErrorCodeContext;
import com.kevinten.vrml.error.exception.ErrorCodeException;

/**
 * The Processor error code exception.
 */
public class ProcessorErrorCodeException extends ErrorCodeException {

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param processorErrorCodeContext the processor error code context
     */
    public ProcessorErrorCodeException(ProcessorErrorCodeContext processorErrorCodeContext) {
        super(processorErrorCodeContext);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     */
    public ProcessorErrorCodeException(ErrorCodeContext errorCodeContext) {
        super(errorCodeContext);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param cause            the cause
     */
    public ProcessorErrorCodeException(ErrorCodeContext errorCodeContext, Throwable cause) {
        super(errorCodeContext, cause);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param errorMessage     the error message
     */
    public ProcessorErrorCodeException(ErrorCodeContext errorCodeContext, String errorMessage) {
        super(errorCodeContext, errorMessage);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param errorMessage     the error message
     * @param cause            the cause
     */
    public ProcessorErrorCodeException(ErrorCodeContext errorCodeContext, String errorMessage, Throwable cause) {
        super(errorCodeContext, errorMessage, cause);
    }
}
