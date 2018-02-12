package org.qcri.xdb.core.plan.operator;

import org.qcri.xdb.core.exception.XdbCoreException;
import org.qcri.xdb.core.plan.expression.XdbExpression;

import java.util.Arrays;

public class XdbComponent {
    private int size;
    private Class[] types;
    private String[] alias;
    private XdbOperator[] connections;
    private XdbExpression[] expressions;
    private String[] expression_alias;

    public XdbComponent(int size){
        this.size = size;
        this.types = new Class[this.size];
        this.alias = new String[this.size];
        this.connections = new XdbOperator[this.size];
        this.expressions = new XdbExpression[this.size];
        this.expression_alias = new String[this.size];
    }


    public Class getType(int index){
        validIndex(index);
        return this.types[index];
    }

    public Class[] getTypes(){
        return this.types;
    }

    public String getAlias(int index){
        validIndex(index);
        return this.alias[index];
    }

    public String[] getAllAlias(){
        return this.alias;
    }

    public XdbOperator getOperator(int index){
        validIndex(index);

        return null;
    }

    public XdbOperator getOperator(String name){
        int index = this.getIndexOperator(name);
        return this.getOperator(index);
    }

    public XdbOperator[] getOperators(){
        return this.connections;
    }

    public XdbExpression getExpression(int index){
        validIndex(index);
        return this.expressions[index];
    }

    public XdbExpression[] getExpressions(){
        return this.expressions;
    }

    public String[] getExpressions_alias() {
        return expression_alias;
    }

    public String getExpression_alias(int index){
        validIndex(index);
        return this.expression_alias[index];
    }

    public void setType(int index, Class clazz){
        validIndex(index);
        this.types[index] = clazz;
    }

    public void setOperator(int index, XdbOperator operator){
        validIndex(index);
        this.connections[index] = operator;
    }

    public void setAlias(int index, String alias){
        validIndex(index);
        this.alias[index] = alias;
    }

    public void setExpression(int index, XdbExpression expression){
        validIndex(index);
        this.expressions[index] = expression;
    }

    public void setExpression_alias(int index, String expression_alias) {
        validIndex(index);
        this.expression_alias[index] = expression_alias;
    }

    public int getSize(){
        return this.size;
    }

    private void validIndex(int index){
        if(index < 0 || index >= this.size){
            throw new XdbCoreException(
                    new ArrayIndexOutOfBoundsException(
                            String.format("the index (%s) is not valid, the range is 0 to %s", index, this.size)
                    )
            );
        }
    }

    private int getIndexOperator(String name){
        for(int i = 0; i < this.size; i++){
            if( name.compareTo( this.connections[i].getAlias() ) == 0){
                return i;
            }
        }
        throw new XdbCoreException(
                String.format("the name (%s) not exist in the connection", name)
        );
    }

    @Override
    public String toString() {
        return "XdbComponents{" +
                "size=" + size +
                ", types=" + Arrays.toString(types) +
                ", alias=" + Arrays.toString(alias) +
                ", expressions=" + Arrays.toString(expressions) +
                ", expression_alias=" + Arrays.toString(expression_alias) +
                ", conexion: "+ Arrays.toString(Arrays.stream(this.connections).map(a -> (a ==null)?"":a.getAlias()).toArray()) +
                '}';
    }
}
