package org.qcri.xdb.translate.json.rheemstudio.engine;

import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.translate.json.rheemstudio.context.TranslateRheemStudioContext;
import org.qcri.xdb.translate.json.rheemstudio.exception.TranslateRheemStudioException;
import org.qcri.xdb.translate.json.rheemstudio.plan.TranslateRheemStudioPlan;

import java.net.URI;
import java.net.URISyntaxException;

public class TranslateRheemStudioEngine extends XdbEngine {
    public TranslateRheemStudioEngine(String name) {
        super(name);
        this.context = new TranslateRheemStudioContext(URI.create(""));

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            this.context = new TranslateRheemStudioContext(classLoader.getResource("rheem.studio.mapping").toURI());
        } catch (URISyntaxException e) {
            throw new TranslateRheemStudioException(e);
        }
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
