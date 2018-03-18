package org.qcri.xdb.translate.rheem.engine;

import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.translate.rheem.context.TranslateRheemContext;
import org.qcri.xdb.translate.rheem.exception.TranslateRheemException;
import org.qcri.xdb.translate.rheem.plan.TranslateRheemPlan;

import java.net.URI;
import java.net.URISyntaxException;

public class TranslateRheemEngine extends XdbEngine {

    public TranslateRheemEngine(String name) {
        super(name);
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            this.context = new TranslateRheemContext(classLoader.getResource("latin.conf.mapping.json").toURI());
        } catch (URISyntaxException e) {
            throw new TranslateRheemException(e);
        }
    }

    @Override
    public XdbPlan preExecute(XdbPlan plan, Object... obj) {
        return new TranslateRheemPlan(plan).generateContext();
    }

    @Override
    public XdbPlan doexecute(XdbPlan plan, Object... obj) {
        System.out.println("TranslatorRheemEngine");
        validPlan(plan);
        return ((TranslateRheemPlan)plan).convert();
    }

    @Override
    public XdbPlan postExecute(XdbPlan plan, Object... obj) {
        validPlan(plan);
        return plan;
    }

    private void validPlan(XdbPlan plan){
        if( ! (plan instanceof TranslateRheemPlan) ){
            throw new TranslateRheemException(
                    String.format(
                            "The class %s not extend of %s",
                            plan.getClass(),
                            TranslateRheemPlan.class
                    )
            );
        }
    }
}
