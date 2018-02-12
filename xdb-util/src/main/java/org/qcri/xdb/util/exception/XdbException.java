package org.qcri.xdb.util.exception;

public class XdbException extends RuntimeException {

    public XdbException(){}

    public XdbException(String message) {
        super(message);
    }

    public XdbException(String message, Throwable cause) {
        super(message, cause);
    }

    public XdbException(Throwable cause) {
        super(cause);
    }

    public XdbException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
