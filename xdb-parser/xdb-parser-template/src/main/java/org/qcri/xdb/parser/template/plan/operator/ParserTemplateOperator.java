package org.qcri.xdb.parser.template.plan.operator;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class ParserTemplateOperator extends XdbOperator {
    public ParserTemplateOperator(String name) {
        super(name);
    }

    public ParserTemplateOperator(XdbElement element) {
        super(element);
    }


    @Override
    protected void selfCopy(XdbElement element) {

    }
}
