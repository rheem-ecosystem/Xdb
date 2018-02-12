package org.qcri.xdb.core.plan.expression;

import org.qcri.xdb.core.plan.XdbElement;

public class ConstantExpression<Type> extends XdbExpression {

    private Type value = null;

    private Class _class = null;

    public ConstantExpression(String name) {
        super(name);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }

    public  ConstantExpression(String name, Type value){
        super(name);
        this.value = value;
        this._class = value.getClass();
    }

    public Type getValue() {
        return value;
    }

    public Class get_class() {
        return _class;
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
        return;
    }

    @Override
    public int countChildren() {
        return 0;
    }
}
