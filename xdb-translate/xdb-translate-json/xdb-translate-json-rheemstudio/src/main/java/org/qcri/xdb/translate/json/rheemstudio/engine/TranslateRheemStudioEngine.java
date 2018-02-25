package org.qcri.xdb.translate.json.rheemstudio.engine;

import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.translate.json.rheemstudio.plan.TranslateRheemStudioPlan;

public class TranslateRheemStudioEngine extends XdbEngine {
    public TranslateRheemStudioEngine(String name) {
        super(name);
    }

    @Override
    public XdbPlan preExecute(XdbPlan plan, Object... obj) {
        return new TranslateRheemStudioPlan(plan);
    }

    @Override
    public XdbPlan doexecute(XdbPlan plan, Object... obj) {
        return plan;

    }

    @Override
    public XdbPlan postExecute(XdbPlan plan, Object... obj) {
        return plan;
    }
}
