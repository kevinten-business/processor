package io.processor.core.response;

/**
 * The Standard processor response.
 */
public class StandardProcessorResponse extends AbstractProcessorResponse {

    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
