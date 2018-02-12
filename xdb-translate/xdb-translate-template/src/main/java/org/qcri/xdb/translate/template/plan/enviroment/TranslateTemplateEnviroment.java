package org.qcri.xdb.translate.template.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;

public class TranslateTemplateEnviroment extends XdbEnviroment {
    protected TranslateTemplateEnviroment(String name) {
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
