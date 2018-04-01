package org.qcri.xdb.core.plan.operator.logical;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.expression.XdbExpression;
import org.qcri.xdb.core.plan.operator.OperatorInput;
import org.qcri.xdb.core.plan.operator.OperatorOutput;
import org.qcri.xdb.core.plan.operator.XdbComponent;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class LoopOperator extends XdbOperator implements OperatorInput, OperatorOutput {

    public static final int INITIAL_INPUT    = 0;
    public static final int ITERATION_INPUT  = 1;
    public static final int CONVERT_INPUT    = 2;
    public static final int ITERATION_OUTPUT = 0;
    public static final int FINAL_OUTPUT     = 1;

    private LoopOperator(String name, int inputs, int outputs){
        super(name);
        this.inputs = new XdbComponent(inputs);
        this.outputs = new XdbComponent(outputs);
    }


    public LoopOperator(String name, boolean hasConvert){
        this(
            name,
            (hasConvert)? 3 : 2,
            2
        );
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
    public String[] getExpressionsAliasOutput() {
        return this.outputs.getExpressions_alias();
    }

    @Override
    public String getExpressionAliasOutput(int index) {
        return this.outputs.getExpression_alias(index);
    }

    @Override
    public void setExpressionAliasOutput(int index, String alias_expression){
        this.outputs.setExpression_alias(index, alias_expression);
    }

    @Override
    public int getSizeOutput() {
        return 2;
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
    public Class getTypeOutput(int index) {
        return this.outputs.getType(index);
    }

    @Override
    public Class[] getTypesOutput() {
        return this.outputs.getTypes();
    }

    @Override
    public String getAliasOutput(int index) {
        return this.outputs.getAlias(index);
    }

    @Override
    public String[] getAllAliasOutput() {
        return this.outputs.getAllAlias();
    }

    @Override
    public XdbOperator getOperatorOutput(int index) {
        return this.outputs.getOperator(index);
    }

    @Override
    public XdbOperator getOperatorOutput(String name) {
        return this.outputs.getOperator(name);
    }

    @Override
    public XdbOperator[] getOperatorsOutput() {
        return this.outputs.getOperators();
    }

    @Override
    public XdbExpression getExpressionOutput(int index) {
        return this.outputs.getExpression(index);
    }

    @Override
    public XdbExpression[] getExpressionsOutput() {
        return this.outputs.getExpressions();
    }

    @Override
    public void setTypeOutput(int index, Class clazz) {
        this.outputs.setType(index, clazz);
    }

    @Override
    public void setOperatorOutput(int index, XdbOperator operator) {
        this.outputs.setOperator(index, operator);
    }

    @Override
    public void setAliasOutput(int index, String alias) {
        this.outputs.setAlias(index, alias);
    }

    @Override
    public void setExpressionOutput(int index, XdbExpression expression) {
        this.outputs.setExpression(index, expression);
    }


    @Override
    protected void selfCopy(XdbElement element) {

    }
}
