package org.qcri.xdb.optimizer.template.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;

public class OptimizerTemplateEnviroment extends XdbEnviroment {
    protected OptimizerTemplateEnviroment(String name) {
        super(name);
    }

    @Override
    public boolean validate() {
        return false;
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
