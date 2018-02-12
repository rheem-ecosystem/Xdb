package org.qcri.xdb.parser.template.plan.enviroment;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;

public class ParserTemplateEnviroment extends XdbEnviroment {
    protected ParserTemplateEnviroment(String name) {
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
