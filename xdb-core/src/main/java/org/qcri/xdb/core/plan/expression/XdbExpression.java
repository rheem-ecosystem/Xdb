package org.qcri.xdb.core.plan.expression;

import org.qcri.xdb.core.plan.XdbElement;

import java.util.List;

public abstract class XdbExpression extends XdbElement{

    protected String real_operator = null;
    protected String type          = null;


    public XdbExpression(String name) {
        super(name);
    }

    public XdbExpression(XdbExpression element) {
        super(element);
    }

    public String getReal_operator() {
        return real_operator;
    }

    public void setReal_operator(String real_operator) {
        this.real_operator = real_operator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "Expression: >"+ this.name +"< "+this.hashCode();
    }

    public abstract boolean hasChildren();

    public abstract boolean hasMoreChildren();

    public abstract XdbExpression nextChildren();

    public abstract void goFirstChildren();

    public abstract int countChildren();

    public boolean isImplemented(){
        return false;
    }

    /** is implemented for the element that can have function implemented */
    public Object getImplementation(){
        return getImplementation(null);
    }

    public Object getImplementation(List<String> broadcast_name){
        return null;
    }
}
