package org.qcri.xdb.parser.template.context;

import org.qcri.xdb.core.context.XdbContext;

import java.net.URI;

public class ParserTemplateContext extends XdbContext {
    public ParserTemplateContext(URI location_configuration) {
        super(location_configuration);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    public void loadContext() {

    }
}
