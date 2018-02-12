package org.qcri.xdb.translate.rheem.plan.expression;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.expression.XdbExpression;

public class TranslateRheemExpression extends XdbExpression {
    public TranslateRheemExpression(String name) {
        super(name);
    }

    public TranslateRheemExpression(XdbExpression element) {
        super(element);
    }

    @Override
    public boolean hasChildren() {
        return false;
    }

    @Override
    public boolean hasMoreChildren() {
        return false;
    }

    @Override
    public XdbExpression nextChildren() {
        return null;
    }

    @Override
    public void goFirstChildren() {

    }

    @Override
    public int countChildren() {
        return 0;
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }
}
