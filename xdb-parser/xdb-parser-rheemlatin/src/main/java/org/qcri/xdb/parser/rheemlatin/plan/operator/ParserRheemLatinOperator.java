package org.qcri.xdb.parser.rheemlatin.plan.operator;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class ParserRheemLatinOperator extends XdbOperator {
    public ParserRheemLatinOperator(String name) {
        super(name);
    }

    public ParserRheemLatinOperator(XdbElement element) {
        super(element);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
