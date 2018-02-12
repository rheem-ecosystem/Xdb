package org.qcri.xdb.core.handler;

import org.qcri.xdb.core.engine.XdbEngine;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.core.query.XdbQuery;

import java.util.Map;

public abstract class XdbHandler {

    protected Map<String, XdbEdge> edges = null;

    protected XdbEdge source;

    public void registre(XdbEngine engine){
        this.edges.put(engine.getName(), new XdbEdge(engine));
    }

    public void toconnect(String source, String consumer){
        XdbEdge edgeSource = this.edges.get(source);
        XdbEdge edgeConsumer = this.edges.get(consumer);
        edgeSource.toConnect(edgeConsumer);
        if(this.source == null){
            this.source = edgeSource;
        }
        if(this.source == edgeConsumer){
            this.source = edgeSource;
        }
    }

    public XdbPlan execute(XdbQuery query){
        XdbPlan plan = query.convert();
        return doExecute(plan);
    }

    protected abstract XdbPlan doExecute(XdbPlan plan);
}
