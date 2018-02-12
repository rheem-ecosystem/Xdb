package org.qcri.xdb.core.exception;

import org.qcri.xdb.util.exception.XdbException;

public class XdbCoreException extends XdbException {

    public XdbCoreException(String s) {
        super(s);
    }

    public XdbCoreException(Exception exception) {
        super(exception);
    }
}
