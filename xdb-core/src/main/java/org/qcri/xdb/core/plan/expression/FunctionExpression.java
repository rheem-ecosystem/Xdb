package org.qcri.xdb.core.plan.expression;

import org.qcri.xdb.core.plan.XdbElement;

public class FunctionExpression extends XdbExpression{
    private int               nparams = 0;

    private int               index_branch = 0;

    private XdbExpression[] params  = null;


    public FunctionExpression(String name) {
        super(name);
    }

    @Override
    protected void selfCopy(XdbElement element) {

    }

    public FunctionExpression(String name, int nparams){
        super(name);
        this.nparams = nparams;
        this.params  = new XdbExpression[this.nparams];
    }

    public FunctionExpression(String name, XdbExpression ... params){
        super(name);
        this.params  = params;
        this.nparams = params.length;
    }

    public void setParams(XdbExpression ... params){
        this.params  = params;
        this.nparams = params.length;
    }

    public void setParam(int index, XdbExpression param) throws IndexOutOfBoundsException{
        if(index < 0 || index >= nparams){
            throw new IndexOutOfBoundsException("index is bad for FunctionExpression");
        }
        this.params[index] = param;
    }

    public XdbExpression[] getParams(){
        return this.params;
    }

    public XdbExpression getParam(int index) throws IndexOutOfBoundsException{
        if(index < 0 || index >= nparams){
            throw new IndexOutOfBoundsException("index is bad for FunctionExpression");
        }
        return this.params[index];
    }

    public int getNparams() {
        return nparams;
    }

    public boolean setNparams(int nparams) {
        if(nparams <= this.nparams){
            return false;
        }

        XdbExpression[] _params = new XdbExpression[nparams];
        for(int i = 0; i < this.params.length; i++){
            _params[i] = this.params[i];
        }
        this.params = _params;
        this.nparams = nparams;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append(this.name);
        builder.append(" ( ");
        for(int i = 0; i < this.nparams; i++) {
            builder.append(this.params[i]);
            builder.append(", ");
        }
        builder.append(" ) ");

        return builder.toString();
    }

    @Override
    public boolean hasChildren() {
        return this.nparams > 0;
    }

    @Override
    public boolean hasMoreChildren() {
        return this.index_branch < this.nparams;
    }

    @Override
    public XdbExpression nextChildren() {
        return this.params[this.index_branch++];
    }

    @Override
    public void goFirstChildren() {
        this.index_branch = 0;
    }

    @Override
    public int countChildren() {
        return this.params.length;
    }
}
