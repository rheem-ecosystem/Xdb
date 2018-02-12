package org.qcri.xdb.core.plan.operator.logical;

import org.qcri.xdb.core.exception.XdbCoreException;
import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.expression.XdbExpression;
import org.qcri.xdb.core.plan.operator.OperatorInput;
import org.qcri.xdb.core.plan.operator.XdbComponent;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class SinkOperator extends XdbOperator implements OperatorInput {

    private String path_source;

    public SinkOperator(String name, int inputs, int outputs){
        super(name);
        if(outputs != 0){
            throw new XdbCoreException(
                    String.format("the operator(%s) is a SourceOperator can't have a inputs")
            );
        }
        this.inputs = new XdbComponent(inputs);
        this.outputs = new XdbComponent(outputs);
    }
    @Override
    protected void selfCopy(XdbElement element) {

    }

    @Override
    public String[] getExpressionsAliasInput() {
        return this.inputs.getExpressions_alias();
    }

    @Override
    public String getExpressionAliasInput(int index) {
        return this.inputs.getExpression_alias(index);
    }

    @Override
    public void setExpressionAliasInput(int index, String alias_expression) {
        this.inputs.setExpression_alias(index, alias_expression);
    }

    @Override
    public Class getTypeInput(int index) {
        return this.inputs.getType(index);
    }

    @Override
    public Class[] getTypesInput() {
        return this.inputs.getTypes();
    }

    @Override
    public String getAliasInput(int index) {
        return this.inputs.getAlias(index);
    }

    @Override
    public String[] getAllAliasInput() {
        return this.inputs.getAllAlias();
    }

    @Override
    public XdbOperator getOperatorInput(int index) {
        return this.inputs.getOperator(index);
    }

    @Override
    public XdbOperator getOperatorInput(String name) {
        return this.inputs.getOperator(name);
    }

    @Override
    public XdbOperator[] getOperatorsInput() {
        return this.inputs.getOperators();
    }

    @Override
    public XdbExpression getExpressionInput(int index) {
        return this.inputs.getExpression(index);
    }

    @Override
    public XdbExpression[] getExpressionsInput() {
        return this.inputs.getExpressions();
    }

    @Override
    public void setTypeInput(int index, Class clazz) {
        this.inputs.setType(index, clazz);
    }

    @Override
    public void setOperatorInput(int index, XdbOperator operator) {
        this.inputs.setOperator(index, operator);
    }

    @Override
    public void setAliasInput(int index, String alias) {
        this.inputs.setAlias(index, alias);
    }

    @Override
    public void setExpressionInput(int index, XdbExpression expression) {
        this.inputs.setExpression(index, expression);
    }

    @Override
    public int getSizeInput() {
        return this.inputs.getSize();
    }

    @Override
    public void changeTypes() {

    }

    public String getPath_source() {
        return path_source;
    }

    public void setPath_source(String path_source) {
        this.path_source = path_source;
    }

}
