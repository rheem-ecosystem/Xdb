package org.qcri.xdb.translate.template.plan.operator;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class TranslateTemplateOperator extends XdbOperator {
    public TranslateTemplateOperator(String name) {
        super(name);
    }

    public TranslateTemplateOperator(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
