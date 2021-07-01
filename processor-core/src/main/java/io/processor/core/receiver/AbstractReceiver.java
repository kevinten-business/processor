package io.processor.core.receiver;

import com.kevinten.processor.common.error.code.ProcessorErrorCodes;
import com.kevinten.processor.common.error.exception.ProcessorErrorCodeException;
import com.kevinten.vrml.error.exception.ErrorCodeException;
import com.kevinten.vrml.trace.MapMdcTraces;
import io.processor.core.context.AbstractProcessorContext;
import io.processor.core.eventbus.event.ProcessorContextCompleteEvent;
import io.processor.core.eventbus.publisher.AsyncProcessorContextEventPublisher;
import io.processor.core.processor.Processor;
import io.processor.core.processor.RecoverProcessor;
import io.processor.core.response.AbstractProcessorResponse;
import io.processor.core.response.ProcessorResponseGenerator;
import io.processor.core.route.RouteHolder;
import io.processor.core.state.Recover;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This module is to receive a context, and than dispatch it to different processor chain to handle.
 *
 * @param <Context>  the context type.
 * @param <Response> the response type.
 */
@Slf4j
public abstract class AbstractReceiver<Context extends AbstractProcessorContext, Response extends AbstractProcessorResponse>
        implements Receiver<Context, Response>, ProcessorResponseGenerator<Response> {

    @Autowired
    private RouteHolder routeHolder;
    @Autowired
    private AsyncProcessorContextEventPublisher asyncProcessorContextEventPublisher;

    /**
     * Start receive process chain
     *
     * @param context context
     * @return process chain response
     */
    private Response start(Context context) {
        Response response = this.makeResponse();
        this.contextDoStart(context);
        this.responseDoStart(response);
        this.metricDoStart(context);
        return response;
    }

    /**
     * Context do start.
     *
     * @param context the context
     */
    protected void contextDoStart(Context context) {
        context.setStartTime(System.currentTimeMillis());
        context.setErrorCode(ProcessorErrorCodes.SUCCESS.getCode());
    }

    /**
     * Response do start.
     *
     * @param response the response
     */
    protected void responseDoStart(Response response) {
        response.runtime().setStartTime(System.currentTimeMillis());
    }

    /**
     * Metric do start.
     *
     * @param context the context
     */
    protected void metricDoStart(Context context) {
        MapMdcTraces.useThreadLocal().initObj(context);
        MapMdcTraces.useThreadLocal().initMdc("messageId", context.getMessageId());
    }

    /**
     * Receive context and process it
     *
     * @param context context
     * @return process chain response
     */
    @Override
    @SuppressWarnings("unchecked")
    public Response receive(Context context) {
        boolean completeChain = false;
        Response response = this.start(context);
        // cache recover processor
        List<RecoverProcessor> recoverCache = new ArrayList<>(2);
        try {
            // get processor chain
            List<Processor> processors = this.routeHolder.getAllProcessors(context);
            if (CollectionUtils.isEmpty(processors)) {
                return response;
            }
            for (Processor processor : processors) {
                if (processor instanceof Recover) {
                    recoverCache.add((RecoverProcessor) processor);
                }
                context.setPreviousRouteEvent(context.getRouteEvent());
                context.setRouteEvent(this.routeHolder.getProcessorEvent(processor));
                {
                    // processor process
                    response = (Response) processor.process(context);
                }
                // break process chain when step failure
                if (this.isProcessFailure(response)) {
                    break;
                }
            }
            // complete success
            completeChain = true;
        } catch (ProcessorErrorCodeException exception) {
            // process exception
            response = this.makeErrorResponse(exception);
            log.info("[AbstractReceiver.ProcessorErrorCodeException] Failed to call receive method, process exception happened.", exception);
        } catch (ErrorCodeException exception) {
            // process exception
            response = this.makeErrorResponse(exception);
            log.info("[AbstractReceiver.ErrorCodeException] Failed to call receive method, process exception happened.", exception);
        } catch (Exception exception) {
            // system exception
            response = this.makeErrorResponse(ProcessorErrorCodes.SYSTEM_ERROR, exception);
            log.error("[AbstractReceiver.Exception] Failed to call receive method, system exception happened!", exception);
        } catch (Throwable throwable) {
            // system throwable
            response = this.makeErrorResponse(ProcessorErrorCodes.SYSTEM_ERROR, throwable);
            log.error("[AbstractReceiver.Throwable] Failed to call receive method, system throwable happened!", throwable);
        } finally {
            // recover
            if (!completeChain) {
                this.recover(context, recoverCache);
            }
            // finish process chain
            this.finish(context, response);
        }
        return response;
    }

    /**
     * Is process failure.
     *
     * @param response the response
     * @return {@code true} will break process chain
     */
    protected boolean isProcessFailure(Response response) {
        return !ProcessorErrorCodes.SUCCESS.getCode().equals(response.getCode());
    }

    private void recover(Context context, List<RecoverProcessor> recoverCache) {
        for (int i = recoverCache.size() - 1; i >= 0; i--) {
            RecoverProcessor recoverProcessor = recoverCache.get(i);
            recoverProcessor.recovery(context);
        }
    }

    private void finish(Context context, Response response) {
        this.contextDoFinish(context, response);
        this.responseDoFinish(response);
        this.metricDoFinish();
        this.eventDoFinish(context, response);
    }

    /**
     * Context do finish.
     *
     * @param context  the context
     * @param response the response
     */
    protected void contextDoFinish(Context context, Response response) {
        context.setErrorCode(response.getCode());
        context.setEndTime(System.currentTimeMillis());
    }

    /**
     * Response do finish.
     *
     * @param response the response
     */
    protected void responseDoFinish(Response response) {
        response.runtime().setEndTime(System.currentTimeMillis());
    }

    /**
     * Metric do finish.
     */
    protected void metricDoFinish() {
        MapMdcTraces.useThreadLocal().clear();
    }

    /**
     * Supplement context and response value and publish a completed event.
     *
     * @param context  context
     * @param response process chain response
     */
    protected void eventDoFinish(Context context, Response response) {
        this.asyncProcessorContextEventPublisher.publishEventAsync(
                new ProcessorContextCompleteEvent(this, context, response));
    }
}