package org.qcri.xdb.core.executor;

import org.qcri.xdb.core.handler.XdbHandler;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.core.query.XdbQuery;

public abstract class XdbExecutor {
    protected XdbHandler handler;

    public XdbExecutor(XdbHandler handler){
        this.handler = handler;
    }

    public Object execute(XdbQuery query){
        XdbPlan plan_final = this.realExecute(query);
        if(plan_final instanceof XdbExecutable){
            return ((XdbExecutable) plan_final).execute();
        }
        return plan_final;
    }

    protected XdbPlan realExecute(XdbQuery query){
        return this.handler.execute(query);
    }
}
