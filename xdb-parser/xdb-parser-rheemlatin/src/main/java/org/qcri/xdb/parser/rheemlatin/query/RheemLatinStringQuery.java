package org.qcri.xdb.parser.rheemlatin.query;

import org.qcri.xdb.core.plan.XdbPlan;
import org.qcri.xdb.core.query.XdbQuery;
import org.qcri.xdb.parser.rheemlatin.query.plan.StringPlan;

public class RheemLatinStringQuery extends XdbQuery {

    private String content;

    public RheemLatinStringQuery(String query_content){
        this.content = query_content;
    }
    @Override
    public XdbPlan convert() {
        return new StringPlan(content);
    }
}
