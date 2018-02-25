package org.qcri.xdb.translate.json.rheemstudio.plan.operator;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class TranslateRheemStudioOperator extends XdbOperator {
    public TranslateRheemStudioOperator(String name) {
        super(name);
    }

    public TranslateRheemStudioOperator(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
