package com.joseph.api.util.result;

public class ResultUtil {

    public ResultUtil(){

    }

    public static boolean isResultSuccess(Result result) {
        return null != result && result.isSuccess();
    }

    public static boolean isResultNotSuccess(Result result) {
        return !isResultSuccess(result);
    }

    public static boolean isModelNotNull(Result result) {
        return isResultSuccess(result) && null != result.getModel();
    }

    public static boolean isModelNull(Result result) {
        return !isModelNotNull(result);
    }

    public static Result defaultResult() {
        return new ResultSupport();
    }

    public static Result successResult(Object model) {
        Result result = defaultResult();
        result.setModel(model);
        return result;
    }

    public static Result failResult(String errorCode, String errorMessage) {
        return failResult(errorCode, errorMessage, false);
    }

    public static Result failResult(String errorMessage) {
        return failResult("-1", errorMessage);
    }

    public static Result failResult(String errorCode, String errorMessage, boolean isI18n) {
        Result result = defaultResult();
        result.setSuccess(false);
        if (isI18n) {
            result.setI18nCode(errorCode);
        } else {
            result.setResultCode(errorCode);
        }

        result.setMessage(errorMessage);
        return result;
    }

    public static Result failResult(String errorCode, String i18nCode, String errorMessage) {
        Result result = defaultResult();
        result.setSuccess(false);
        result.setResultCode(errorCode);
        result.setI18nCode(i18nCode);
        result.setMessage(errorMessage);
        return result;
    }
}
