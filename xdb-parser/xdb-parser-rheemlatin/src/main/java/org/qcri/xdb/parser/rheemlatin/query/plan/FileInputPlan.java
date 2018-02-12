package org.qcri.xdb.parser.rheemlatin.query.plan;

import org.qcri.xdb.core.plan.SpecialPlan;
import org.qcri.xdb.core.plan.XdbPlan;

public class FileInputPlan extends XdbPlan implements SpecialPlan {

    public String path;

    public FileInputPlan(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
