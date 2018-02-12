package org.qcri.xdb.optimizer.template.exception;

import org.qcri.xdb.util.exception.XdbException;

public class OptimizerTemplateException extends XdbException {

    public OptimizerTemplateException(String s) {
        super(s);
    }

    public OptimizerTemplateException(Exception exception) {
        super(exception);
    }
}
