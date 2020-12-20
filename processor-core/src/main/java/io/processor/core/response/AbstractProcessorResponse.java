package io.processor.core.response;

import com.kevinten.vrml.error.code.ErrorCodeContext;
import com.kevinten.vrml.error.exception.ErrorCodeException;
import com.kevinten.processor.common.error.code.ProcessorErrorCodes;

/**
 * The Abstract processor response.
 */
public abstract class AbstractProcessorResponse {

    // -- Fields

    private String code;
    private String message;
    private Throwable cause;

    /**
     * The runtime values of the (request-response) process
     */
    private final ResponseRuntime responseRuntime = new ResponseRuntime();

    /**
     * Initialize the default constructor.
     */
    public AbstractProcessorResponse() {
        this(ProcessorErrorCodes.SUCCESS);
    }

    /**
     * Initialize the constructor with the given error info.
     *
     * @param errorInfo the error info.
     */
    public AbstractProcessorResponse(ErrorCodeContext errorInfo) {
        this(errorInfo, null);
    }

    /**
     * Initialize the constructor with the given exception.
     *
     * @param exception the exception.
     */
    public AbstractProcessorResponse(ErrorCodeException exception) {
        this.code = exception.getErrorCode();
        this.message = exception.getMessage();
        this.cause = exception.getCause();
    }

    /**
     * Initialize the constructor with the given error info and cause.
     *
     * @param errorInfo the error info
     * @param cause     the cause
     */
    public AbstractProcessorResponse(ErrorCodeContext errorInfo, Throwable cause) {
        this.code = errorInfo.getCode();
        this.message = errorInfo.getMessage();
        this.cause = cause;
    }

    /**
     * The Response runtime properties.
     */
    public static class ResponseRuntime {

        private long startTime;
        private long endTime;
        private String messageId;

        public long getStartTime() {
            return startTime;
        }

        public void setStartTime(long startTime) {
            this.startTime = startTime;
        }

        public long getEndTime() {
            return endTime;
        }

        public void setEndTime(long endTime) {
            this.endTime = endTime;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }
    }

    // -- Encapsulation

    /**
     * return the error code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the error code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the error message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Returns the cause if this is a bad response.
     *
     * @return the cause
     */
    public Throwable getCause() {
        return cause;
    }

    /**
     * Sets the cause if this is a bad response.
     *
     * @param cause the cause
     */
    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    /**
     * Returns the runtime message for response.
     *
     * @return the response runtime
     */
    public ResponseRuntime runtime() {
        return responseRuntime;
    }
}
