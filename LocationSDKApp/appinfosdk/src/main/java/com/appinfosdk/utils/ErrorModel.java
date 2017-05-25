package com.appinfosdk.utils;

/**
 * Created by prashant.patel on 5/25/2017.
 */

public class ErrorModel
{
    public Exception exception;
    public String statusCode="500";

    public Exception getException() {
        return exception;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
