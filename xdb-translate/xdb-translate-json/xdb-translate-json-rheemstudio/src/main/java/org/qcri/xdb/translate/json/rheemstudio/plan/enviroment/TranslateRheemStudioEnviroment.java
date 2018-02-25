package org.qcri.xdb.translate.json.rheemstudio.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;

public class TranslateRheemStudioEnviroment extends XdbEnviroment {
    protected TranslateRheemStudioEnviroment(String name) {
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
