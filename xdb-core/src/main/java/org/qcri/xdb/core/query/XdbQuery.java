package org.qcri.xdb.core.query;

import org.qcri.xdb.core.plan.XdbPlan;

public abstract class XdbQuery {

    public abstract XdbPlan convert();
}
