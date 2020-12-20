package io.processor.template.error.exception;

import com.kevinten.vrml.error.code.ErrorCodeContext;
import com.kevinten.vrml.error.exception.ErrorCodeException;
import com.kevinten.processor.common.error.code.ProcessorErrorCodeContext;

/**
 * 配合你自己定义的错误码规范{@link io.processor.template.error.code.TemplateErrorCodes}，
 * 定义语义清晰的业务异常类型（因为Processor系统的机制是使用业务异常来中断任务链）
 * 需要继承{@link ErrorCodeException}
 * <p>
 * The Processor error code exception.
 */
public class TemplateErrorCodeException extends ErrorCodeException {

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param processorErrorCodeContext the processor error code context
     */
    public TemplateErrorCodeException(ProcessorErrorCodeContext processorErrorCodeContext) {
        super(processorErrorCodeContext);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     */
    public TemplateErrorCodeException(ErrorCodeContext errorCodeContext) {
        super(errorCodeContext);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param cause            the cause
     */
    public TemplateErrorCodeException(ErrorCodeContext errorCodeContext, Throwable cause) {
        super(errorCodeContext, cause);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param errorMessage     the error message
     */
    public TemplateErrorCodeException(ErrorCodeContext errorCodeContext, String errorMessage) {
        super(errorCodeContext, errorMessage);
    }

    /**
     * Instantiates a new Processor error code exception.
     *
     * @param errorCodeContext the error code context
     * @param errorMessage     the error message
     * @param cause            the cause
     */
    public TemplateErrorCodeException(ErrorCodeContext errorCodeContext, String errorMessage, Throwable cause) {
        super(errorCodeContext, errorMessage, cause);
    }
}
