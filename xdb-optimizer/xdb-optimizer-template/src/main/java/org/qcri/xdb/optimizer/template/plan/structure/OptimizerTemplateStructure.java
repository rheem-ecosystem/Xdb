package org.qcri.xdb.optimizer.template.plan.structure;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.structure.XdbStructure;

public class OptimizerTemplateStructure extends XdbStructure {
    public OptimizerTemplateStructure(String name) {
        super(name);
    }

    public OptimizerTemplateStructure(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
