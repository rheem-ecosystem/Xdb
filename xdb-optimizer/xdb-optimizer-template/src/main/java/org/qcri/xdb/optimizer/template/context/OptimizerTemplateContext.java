package org.qcri.xdb.optimizer.template.context;

import org.qcri.xdb.core.context.XdbContext;

import java.net.URI;

public class OptimizerTemplateContext extends XdbContext {
    public OptimizerTemplateContext(URI location_configuration) {
        super(location_configuration);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    public void loadContext() {

    }
}