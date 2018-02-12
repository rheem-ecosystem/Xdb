package org.qcri.xdb.translate.rheem.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;

public class TranslateRheemEnviroment extends XdbEnviroment {
    protected TranslateRheemEnviroment(String name) {
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
