package io.processor.core.processor;

import com.kevinten.processor.common.error.code.ProcessorErrorCodes;
import com.kevinten.processor.common.error.exception.ProcessorErrorCodeException;
import com.kevinten.vrml.error.exception.ErrorCodeException;
import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.eventbus.event.ProcessorContextStepEvent;
import io.processor.core.eventbus.publisher.AsyncProcessorContextEventPublisher;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.response.ProcessorResponseGenerator;
import io.processor.core.route.key.RouteKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This module is to process a context of different processor chain.
 *
 * @param <Context>  the context type.
 * @param <Response> the response type.
 * @param <Route>    the route key type.
 */
@Slf4j
public abstract class AbstractProcessor<Context extends AbstractProcessorContext, Response extends AbstractProcessorResponse, Route extends RouteKey>
        implements Processor<Context, Response, Route>, ProcessorResponseGenerator<Response> {

    @Autowired
    private AsyncProcessorContextEventPublisher asyncProcessorContextEventPublisher;

    /**
     * Start process
     *
     * @return process response
     */
    private Response startProcess() {
        Response response = this.makeResponse();
        this.responseDoProcessStart(response);
        return response;
    }

    /**
     * Response do process start.
     *
     * @param response the response
     */
    protected void responseDoProcessStart(Response response) {
        response.runtime().setStartTime(System.currentTimeMillis());
    }

    /**
     * To do what you want to do.
     *
     * @param context the context to handle
     * @throws ProcessorErrorCodeException the processor error code exception
     */
    protected abstract void doProcess(Context context) throws ProcessorErrorCodeException;

    @Override
    public Response process(Context context) {
        Response response = this.startProcess();
        try {
            this.doProcess(context);
        } catch (ProcessorErrorCodeException exception) {
            // process exception
            response = this.makeErrorResponse(exception);
            log.warn("[AbstractProcessor.ProcessorErrorCodeException] Failed to call process method, because a known exception occurred, please ignore.", exception);
        } catch (ErrorCodeException exception) {
            // process exception
            response = this.makeErrorResponse(exception);
            log.warn("[AbstractProcessor.ErrorCodeException] Failed to call process method, because a known exception occurred, please ignore.", exception);
        } catch (Exception exception) {
            // system exception
            response = this.makeErrorResponse(ProcessorErrorCodes.SYSTEM_ERROR, exception);
            log.warn("[AbstractProcessor.Exception] An unknown exception occurred, please handle it as soon as possible!", exception);
        } finally {
            // finish process
            this.finish(context, response);
        }
        return response;
    }

    private void finish(Context context, Response response) {
        this.contextDoProcessFinish(context, response);
        this.responseDoProcessFinish(response);
        this.eventDoProcessFinish(context, response);
    }

    /**
     * Context do process finish.
     *
     * @param context  the context
     * @param response the response
     */
    protected void contextDoProcessFinish(Context context, Response response) {
        context.setErrorCode(response.getCode());
        context.setEndTime(System.currentTimeMillis());
    }

    /**
     * Response do process finish.
     *
     * @param response the response
     */
    protected void responseDoProcessFinish(Response response) {
        response.runtime().setEndTime(System.currentTimeMillis());
    }

    /**
     * Supplement context and response value and publish a step event.
     *
     * @param context  context
     * @param response process response
     */
    protected void eventDoProcessFinish(Context context, Response response) {
        this.asyncProcessorContextEventPublisher.publishEventAsync(
                new ProcessorContextStepEvent(this, context, response));
    }
}
