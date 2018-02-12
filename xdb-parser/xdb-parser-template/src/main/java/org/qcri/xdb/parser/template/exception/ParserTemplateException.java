package org.qcri.xdb.parser.template.exception;

import org.qcri.xdb.util.exception.XdbException;

public class ParserTemplateException extends XdbException {

    public ParserTemplateException(String s) {
        super(s);
    }

    public ParserTemplateException(Exception exception) {
        super(exception);
    }
}
