package org.qcri.xdb.optimizer.template.plan.operator;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class OptimizerTemplateOperator extends XdbOperator {
    public OptimizerTemplateOperator(String name) {
        super(name);
    }

    public OptimizerTemplateOperator(XdbElement element) {
        super(element);
    }


    @Override
    protected void selfCopy(XdbElement element) {

    }
}
