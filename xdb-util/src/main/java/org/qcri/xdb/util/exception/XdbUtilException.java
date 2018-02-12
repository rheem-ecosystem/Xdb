package org.qcri.xdb.util.exception;

public class XdbUtilException extends XdbException {

    public XdbUtilException(){}

    public XdbUtilException(String message) {
        super(message);
    }

    public XdbUtilException(String message, Throwable cause) {
        super(message, cause);
    }

    public XdbUtilException(Throwable cause) {
        super(cause);
    }

    public XdbUtilException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
