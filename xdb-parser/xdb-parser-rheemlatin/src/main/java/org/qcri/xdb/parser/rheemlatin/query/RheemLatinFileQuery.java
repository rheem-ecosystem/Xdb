package org.qcri.xdb.parser.rheemlatin.query;

import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.core.query.XdbQuery;
import org.qcri.xdb.parser.rheemlatin.query.plan.FileInputPlan;

public class RheemLatinFileQuery extends XdbQuery {

    private String path;

    public RheemLatinFileQuery(String query_path){
        this.path = query_path;
    }

    @Override
    public XdbPlan convert() {
        return new FileInputPlan(this.path);
    }
}
