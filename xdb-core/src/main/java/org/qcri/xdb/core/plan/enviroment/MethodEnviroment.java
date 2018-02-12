package org.qcri.xdb.core.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;

public class MethodEnviroment extends XdbEnviroment {

    protected MethodEnviroment(String name) {
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
