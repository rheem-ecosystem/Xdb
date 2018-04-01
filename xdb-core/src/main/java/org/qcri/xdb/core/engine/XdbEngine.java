package org.qcri.xdb.core.engine;

import org.qcri.xdb.core.context.XdbContext;
import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.util.exception.XdbException;

public abstract class XdbEngine {
    protected String name = null;

    protected XdbContext context = null;

    public XdbEngine(String name){
        this.name = name;
    }

    public void load(){
        try {
            context.loadContext();
        } catch (XdbException e) {
            e.printStackTrace();
            System.err.println(this.getClass());
            System.exit(-100);
        }
    }

    public XdbPlan execute(XdbPlan plan, Object ... obj){
        this.load();
        XdbPlan _plan = this.preExecute(plan, obj);
        _plan = this.doexecute(_plan, obj);
        return this.postExecute(_plan, obj);
    }

    public abstract XdbPlan preExecute(XdbPlan plan, Object ... obj);

    public abstract XdbPlan doexecute(XdbPlan plan, Object ... obj);

    public abstract XdbPlan postExecute(XdbPlan plan, Object ... obj);

    public Object[] getParameters(){
        return null;
    }

    public String getName(){
        return this.name;
    }
}
