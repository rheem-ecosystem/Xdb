package org.qcri.xdb.translate.template.exception;

import org.qcri.xdb.util.exception.XdbException;

public class TranslateTemplateException extends XdbException {

    public TranslateTemplateException(String s) {
        super(s);
    }

    public TranslateTemplateException(Exception exception) {
        super(exception);
    }
}
