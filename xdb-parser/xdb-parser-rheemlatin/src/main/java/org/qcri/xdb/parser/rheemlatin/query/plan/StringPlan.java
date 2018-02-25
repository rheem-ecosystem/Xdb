package org.qcri.xdb.parser.rheemlatin.query.plan;

import org.qcri.xdb.core.plan.SpecialPlan;
import org.qcri.xdb.core.plan.XdbPlan;

public class StringPlan extends XdbPlan implements SpecialPlan {
    private String planString;


    public StringPlan(String planString){
        this.planString = planString;
    }

    public String getPlanString(){
        return this.planString;
    }
}
