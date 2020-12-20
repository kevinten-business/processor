package io.processor.core.response;


import com.kevinten.vrml.error.code.ErrorCodeContext;
import com.kevinten.vrml.error.exception.ErrorCodeException;

/**
 * The Processor response generator.
 *
 * @param <Response> the response type
 */
public interface ProcessorResponseGenerator<Response extends AbstractProcessorResponse> {

    /**
     * Make response.
     *
     * @return the response
     */
    Response makeResponse();

    /**
     * Make error response.
     *
     * @param exception the exception
     * @return the response
     */
    default Response makeErrorResponse(ErrorCodeException exception) {
        Response response = this.makeResponse();
        response.setCode(exception.getErrorCode());
        response.setMessage(exception.getErrorMessage());
        response.setCause(exception);
        return response;
    }

    /**
     * Make error response.
     *
     * @param errorCodeContext the error code context
     * @param exception        the exception
     * @return the response
     */
    default Response makeErrorResponse(ErrorCodeContext errorCodeContext, Throwable exception) {
        Response response = this.makeResponse();
        response.setCode(errorCodeContext.getCode());
        response.setMessage(errorCodeContext.getMessage());
        response.setCause(exception);
        return response;
    }
}
