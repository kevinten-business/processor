package io.processor.application.processor.error.code;

import com.kevinten.processor.common.error.code.ProcessorErrorCodeContext;

/**
 * Application error info context:
 * [20000] SUCCESS
 * [10xxx] {@code parameter} error. Define your parameter check exception
 * [20xxx] {@code business} error. Define your business logic exception
 * [30xxx] {@code repository service}. Define repository operation exception
 * [40xxx] {@code dependent service}. Define dependency service exception
 * [50xxx] {@code system} error. Define application system exception
 */
public enum AppErrorCodes implements ProcessorErrorCodeContext {

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
    PARAMETER_FLIGHT_CITY_ERROR(
            GENERATOR.createParameterErrorCode("001"), "Invalid flight city error!"),
    PARAMETER_HOTEL_NAME_ERROR(
            GENERATOR.createParameterErrorCode("002"), "Invalid hotel name error!"),

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

    AppErrorCodes(String code, String message) {
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
        ErrorCodeManager.assertErrorCodesNoDuplicate(AppErrorCodes.values());
    }

    /**
     * Show error codes.
     */
    public static void showErrorCodes() {
        MANAGER.showErrorCodes(AppErrorCodes.values());
    }
}
