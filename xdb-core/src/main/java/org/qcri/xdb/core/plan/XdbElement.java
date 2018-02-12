package org.qcri.xdb.core.plan;

public abstract class XdbElement {

    protected String name;

    public XdbElement(String name){
        this.name = name;
    }

    public XdbElement(XdbElement element){
        selfCopy(element);
    }

    protected abstract void selfCopy(XdbElement element);

    public String getName(){
        return this.name;
    }
}
