package org.qcri.xdb.optimizer.template.engine;

import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.plan.XdbPlan;

public class OptimizerTemplateEngine extends XdbEngine {
    public OptimizerTemplateEngine(String name) {
        super(name);
    }

    @Override
    public XdbPlan preExecute(XdbPlan plan, Object... obj) {
        return null;
    }

    @Override
    public XdbPlan doexecute(XdbPlan plan, Object... obj) {
        return null;
    }

    @Override
    public XdbPlan postExecute(XdbPlan plan, Object... obj) {
        return null;
    }
}
