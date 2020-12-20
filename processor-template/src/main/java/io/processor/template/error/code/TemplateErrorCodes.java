package io.processor.template.error.code;

import com.kevinten.processor.common.error.code.ProcessorErrorCodeContext;

/**
 * 定义你自己的错误码规范，需要实现{@link ProcessorErrorCodeContext}。
 * 基于以下五大类的基本规范，实现细粒度的错误码语义。
 * <p>
 * Application error info context:
 * [20000] SUCCESS
 * [10xxx] {@code parameter} error. Define your parameter check exception
 * [20xxx] {@code business} error. Define your business logic exception
 * [30xxx] {@code repository service}. Define repository operation exception
 * [40xxx] {@code dependent service}. Define dependency service exception
 * [50xxx] {@code system} error. Define application system exception
 */
public enum TemplateErrorCodes implements ProcessorErrorCodeContext {

    /**
     * The successful error code.
     */
    SUCCESS("20000", "Success"),

    /*-------------------------------------------Parameter error as below---------------------------------------**/
    /**
     * Invalid basic parameter error, the code starts with 0.
     */
    PARAMETER_ERROR(
            GENERATOR.createParameterErrorCode("000"), "Invalid parameter error!"),

    /*-------------------------------------------Business error as below---------------------------------------**/
    /**
     * Basic business error, the code starts with 0.
     */
    BUSINESS_ERROR(
            GENERATOR.createBusinessProcessErrorCode("001"), "Business error!"),

    /*-------------------------------------------Repository error as below---------------------------------------**/
    /**
     * Basic repository error, the code starts with 0.
     */
    REPOSITORY_ERROR(
            GENERATOR.createRepositoryErrorCode("000"), "Repository error!"),

    /*-------------------------------------------Dependent service error as below---------------------------------------**/
    /**
     * Basic dependent service error, the code starts with 0.
     */
    DEPENDENT_SERVICE_ERROR(
            GENERATOR.createDependentServiceErrorCode("000"), "Failed to call the dependent service!"),

    /*-------------------------------------------System error as below---------------------------------------**/
    /**
     * Basic system error, the code starts with 0.
     */
    SYSTEM_ERROR(
            GENERATOR.createSystemErrorCode("000"), "System error!"),
    ;

    // -- Encapsulation

    private final String code;
    private final String message;

    TemplateErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    static {
        ErrorCodeManager.assertErrorCodesNoDuplicate(TemplateErrorCodes.values());
    }

    /**
     * Show error codes.
     */
    public static void showErrorCodes() {
        MANAGER.showErrorCodes(TemplateErrorCodes.values());
    }
}
