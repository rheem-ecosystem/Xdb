package org.qcri.xdb.core.plan;

import org.qcri.xdb.core.plan.enviroment.XdbEnviroment;
import org.qcri.xdb.core.plan.expression.XdbExpression;
import org.qcri.xdb.core.plan.operator.XdbOperator;
import org.qcri.xdb.core.plan.operator.logical.SinkOperator;
import org.qcri.xdb.core.plan.operator.logical.SourceOperator;
import org.qcri.xdb.core.plan.structure.XdbStructure;

import java.util.Collection;
import java.util.Map;

public abstract class XdbPlan {


    protected Collection<XdbOperator> operators      = null;

    protected Collection<XdbExpression>      expressions    = null;

    protected Collection<String>               alias          = null;

    protected Map<String, XdbOperator> aliasOperators = null;

    protected Collection<SourceOperator>       sourceOperators= null;

    protected Collection<SinkOperator>         sinkOperators  = null;

    protected Collection<XdbStructure>       structures     = null;

    protected Map<String, XdbEnviroment>     enviroments    = null;

    public XdbPlan(){}

    public Collection<XdbOperator> getOperators() {
        return operators;
    }

    public void setOperators(Collection<XdbOperator> operators) {
        this.operators = operators;
    }

    public Collection<XdbExpression> getExpressions() {
        return expressions;
    }

    public void setExpressions(Collection<XdbExpression> expressions) {
        this.expressions = expressions;
    }

    public Collection<String> getAlias() {
        return alias;
    }

    public void setAlias(Collection<String> alias) {
        this.alias = alias;
    }

    public Map<String, XdbOperator> getAliasOperators() {
        return aliasOperators;
    }

    public void setAliasOperators(Map<String, XdbOperator> aliasOperators) {
        this.aliasOperators = aliasOperators;
    }

    public Collection<SourceOperator> getSourceOperators() {
        return sourceOperators;
    }

    public void setSourceOperators(Collection<SourceOperator> sourceOperators) {
        this.sourceOperators = this.sourceOperators;
    }

    public Collection<SinkOperator> getSinkOperators() {
        return sinkOperators;
    }

    public void setSinkOperators(Collection<SinkOperator> sinkOperators) {
        this.sinkOperators = sinkOperators;
    }

    public Collection<XdbStructure> getStructures() {
        return structures;
    }

    public void setStructures(Collection<XdbStructure> structures) {
        this.structures = structures;
    }

    public Map<String, XdbEnviroment> getEnviroments() {
        return enviroments;
    }

    public void setEnviroments(Map<String, XdbEnviroment> enviroments) {
        this.enviroments = enviroments;
    }

    public void print(){
        if (this.enviroments != null) {
            for (XdbEnviroment env : this.enviroments.values()) {
                System.err.println("env: "+env);
            }
        }
        if (this.expressions != null){
            for(XdbExpression expr: this.expressions){
                System.err.println("expr: "+expr);
            }
        }
        if (this.structures != null) {
            for (XdbStructure str : this.structures) {
                System.err.println("stru: "+str);
            }
        }
        for(XdbOperator tmp: this.operators){
            System.err.println("ope: "+tmp);
        }
    }
}
