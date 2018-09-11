package br.com.valecard.test.service;

import br.com.valecard.test.model.response.ErrorResponse;

public class BaseResponse<T> {

    private T successResponse;
    private ErrorResponse errorResponse;

    public BaseResponse(T successResponse, ErrorResponse errorResponse) {
        this.successResponse = successResponse;
        this.errorResponse = errorResponse;
    }

    public T getSuccessResponse() {
        return successResponse;
    }

    public void setSuccessResponse(T successResponse) {
        this.successResponse = successResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }
}
