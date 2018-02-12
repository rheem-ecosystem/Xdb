package org.qcri.xdb.core.handler.handlers;

import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.handler.XdbEdge;
import org.qcri.xdb.core.handler.XdbHandler;
import org.qcri.xdb.core.plan.XdbPlan;

import java.util.HashMap;

public class LinealHandler extends XdbHandler{

    public LinealHandler(){
        this.edges = new HashMap<>();
    }
    @Override
    protected XdbPlan doExecute(XdbPlan plan) {
        if(this.source == null){
            this.source = (XdbEdge) this.edges.values().toArray()[0];
        }
        XdbEdge current_engine = this.source;
        XdbPlan current_plan   = plan;
        while (current_engine != null) {
            XdbEngine engine = current_engine.getEngine();
            Object[] parameters = engine.getParameters();
            current_plan = engine.execute(current_plan, parameters);
            current_engine = current_engine.getNext(0);
        }
        return current_plan;
    }
}
