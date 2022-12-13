package com.example.digiwallet.exception;

public class DigiException extends Exception {

    private final String exceptionType;

    public DigiException(String exceptionType) {
        super();
        this.exceptionType = exceptionType;
    }

    public DigiException(String message, String exceptionType) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public DigiException(String message, Throwable cause, String exceptionType) {
        super(message, cause);
        this.exceptionType = exceptionType;
    }

    public DigiException(Throwable cause, String exceptionType) {
        super(cause);
        this.exceptionType = exceptionType;
    }

    protected DigiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String exceptionType) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.exceptionType = exceptionType;
    }

    public String getExceptionType() {
        return exceptionType;
    }
}
