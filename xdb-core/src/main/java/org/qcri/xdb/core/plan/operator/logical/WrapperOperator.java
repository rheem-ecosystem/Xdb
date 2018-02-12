package org.qcri.xdb.core.plan.operator.logical;

import org.qcri.xdb.core.exception.XdbCoreException;
import org.qcri.xdb.core.plan.expression.RealFunctionExpression;
import org.qcri.xdb.core.plan.operator.OperatorInput;
import org.qcri.xdb.core.plan.operator.XdbOperator;

import java.util.*;

public class WrapperOperator extends ManyOperator {

    private Map<String, XdbOperator> operators;

    private List<String> alias_inputs;
    private List<String> alias_output;

    private Map<String, RealFunctionExpression> expressionMap;

    {
        if(this.expressionMap == null) {
            this.expressionMap = new HashMap<>();
        }
    }

    public WrapperOperator(String name, int inputs, int outputs) {
        super(name, inputs, outputs);
    }

    public Map<String, XdbOperator> getWrapped(){
        return this.operators;
    }

    public void setOperators(Map<String, XdbOperator> operators) {
        this.operators = operators;
    }

    @Override
    public void setAlias(String alias) {
        this.alias = alias;

        Set<Map.Entry<String, XdbOperator>> setEntry = this.operators.entrySet();
        HashMap<String, XdbOperator> map_new = new HashMap<>();

        for(Map.Entry<String, XdbOperator> entry: setEntry){
            XdbOperator tmp = entry.getValue();
            tmp.setAlias(String.format(tmp.getAlias(), this.alias));

            if(tmp instanceof OperatorInput){
                OperatorInput tmp_input = (OperatorInput) tmp;
                for(int i = 0; i < tmp_input.getSizeInput(); i++) {
                    tmp_input.setAliasInput(
                            i,
                            String.format(
                                    tmp_input.getAliasInput(i),
                                    this.alias
                            )
                    );
                }
            }


            map_new.put(tmp.getAlias(), tmp);
        }

        ArrayList<String> tmp_output = new ArrayList<>();
        for(String tmp: this.alias_output){
            tmp_output.add(
                    String.format(tmp, this.alias)
            );
        }

        this.alias_output = tmp_output;
        this.operators = map_new;


    }

    public List<String> getAlias_inputs() {
        return alias_inputs;
    }

    public void setAlias_inputs(List<String> alias_inputs) {
        this.alias_inputs = alias_inputs;
    }

    public List<String> getAlias_output() {
        return alias_output;
    }

    public void setAlias_output(List<String> alias_outpus) {
        this.alias_output = alias_outpus;
    }

    public String getAliasInputReal(String name){
        int position = this.alias_inputs.indexOf(name);
        if(position < 0){
            //TODO crear un mejor detalle dentro del mensaje
            throw new XdbCoreException(
                    String.format("No exist the reference to %s", name)
            );
        }

        return this.getAliasInput(position);
    }

    public  RealFunctionExpression getExpressionMap(String key) {
        return expressionMap.get(key);
    }

    public void putExpressionMap(String key, RealFunctionExpression expressionMap) {
        this.expressionMap.put(key, expressionMap);
    }
}
