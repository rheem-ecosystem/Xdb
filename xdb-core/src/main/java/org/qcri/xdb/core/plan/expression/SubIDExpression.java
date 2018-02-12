package org.qcri.xdb.core.plan.expression;

import org.qcri.xdb.core.plan.XdbElement;

public class SubIDExpression extends XdbExpression {


    private String value;

    public SubIDExpression(String name) {
        super(name);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }

    public SubIDExpression(String name, String value) {
        super(name);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean hasChildren() {
        return true;
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


    //TODO: crear de forma generica para todas las opciones disponibles
    public String getReference(){
        return value.split("\\.")[0];
    }

    public String getComponente(){
        return value.split("\\.")[1];
    }
}
