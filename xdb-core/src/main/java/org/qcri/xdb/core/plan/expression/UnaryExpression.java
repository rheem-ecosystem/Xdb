package org.qcri.xdb.core.plan.expression;

import org.qcri.xdb.core.plan.XdbElement;

public class UnaryExpression extends XdbExpression {
    private XdbExpression branch = null;



    public UnaryExpression(String name) {
        super(name);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }

    public UnaryExpression(String name, XdbExpression expr){
        super(name);
        this.branch = expr;
    }

    public XdbExpression getBranch() {
        return branch;
    }

    public void setBranch(XdbExpression branch) {
        this.branch = branch;
    }

    @Override
    public boolean hasChildren() {
        return this.branch != null;
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
        return;
    }

    @Override
    public int countChildren() {
        return 1;
    }
}
