package org.qcri.xdb.core.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;

public abstract class XdbEnviroment extends XdbElement {

    protected XdbEnviroment(String name) {
        super(name);
    }

    public abstract boolean validate();
}
