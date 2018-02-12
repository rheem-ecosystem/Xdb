package org.qcri.xdb.core.plan.operator.logical;

import org.qcri.xdb.core.plan.XdbElement;
import org.qcri.xdb.core.plan.expression.RealFunctionExpression;
import org.qcri.xdb.core.plan.expression.XdbExpression;
import org.qcri.xdb.core.plan.operator.OperatorInput;
import org.qcri.xdb.core.plan.operator.OperatorOutput;
import org.qcri.xdb.core.plan.operator.XdbComponent;
import org.qcri.xdb.core.plan.operator.XdbOperator;

public class ManyOperator extends XdbOperator implements OperatorInput, OperatorOutput {

    public ManyOperator(String name, int inputs, int outputs){
        super(name);
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
    public int getSizeOutput() {
        return this.outputs.getSize();
    }

    public void changeTypes() {
        //TODO implemetar este metodo de forma parametrica y para los casos en los que se tiene mas de un output
        /*if (this.get == null) {
            if (this.ninputs == 1) {
                this.classOutput = this.getClassInput(0);
            } else {
                throw new exception("Error Class Output invalid for " + this.alias);
            }
        }*/
        for(int j = 0; j < this.getSizeInput(); j++){
            Class type_return;
            if(this.getExpressionInput(j) instanceof RealFunctionExpression){
                RealFunctionExpression real_func = (RealFunctionExpression)this.getExpressionInput(j);
                type_return = real_func.getType_return();
            }else{
                type_return = this.getTypeInput(j);
            }
            //TODO hacer que este caso sea parametrico, cuando el return es un iterable pero lo que importa es el contenido
            //TODO como pasa en el caso del flatmap
            if(this.getTypeOutput(0) != null){
                if(this.getName().compareToIgnoreCase("FLATMAP") == 0){
                    this.setTypeOutput(0, type_return);
                }
            }
            if(this.getTypeOutput(0) == null ) {
                this.setTypeOutput(0, type_return);
            }
        }
    }
}
