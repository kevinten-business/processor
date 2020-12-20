package com.kevinten.processor.common.error.code;


import com.kevinten.vrml.error.code.ErrorCodeContext;

/**
 * The Processor error code context.
 */
public interface ProcessorErrorCodeContext extends ErrorCodeContext {

    /**
     * The ProcessorErrorCode GENERATOR.
     */
    ProcessorErrorCodeGenerator GENERATOR = new ProcessorErrorCodeGenerator();
    /**
     * The ProcessorErrorCode MANAGER.
     */
    ProcessorErrorCodeManager MANAGER = new ProcessorErrorCodeManager();

    /**
     * The Processor error code generator.
     */
    class ProcessorErrorCodeGenerator implements ErrorCodeGenerator {

        /**
         * Processor system code.
         *
         * @apiNote Set a single unique code for your application.
         */
        private static final String PROCESSOR_APPLICATION_CODE = "0";

        @Override
        public String applicationErrorCode() {
            return PROCESSOR_APPLICATION_CODE;
        }
    }

    /**
     * The Processor error code manager.
     */
    class ProcessorErrorCodeManager implements ErrorCodeManager<ProcessorErrorCodeContext> {

        @Override
        public void showErrorCodeItem(ProcessorErrorCodeContext errorCodeContext) {
            System.out.printf("%70s  %5s  %s", errorCodeContext.name(), errorCodeContext.getCode(), errorCodeContext.getMessage());
        }
    }
}
