package org.qcri.xdb.core.plan.operator;

import org.qcri.xdb.core.plan.expression.XdbExpression;

public interface OperatorOutput {
    Class getTypeOutput(int index);

    Class[] getTypesOutput();

    String getAliasOutput(int index);

    String[] getAllAliasOutput();

    XdbOperator getOperatorOutput(int index);

    XdbOperator getOperatorOutput(String name);

    XdbOperator[] getOperatorsOutput();

    XdbExpression getExpressionOutput(int index);

    XdbExpression[] getExpressionsOutput();

    String[] getExpressionsAliasOutput();

    String getExpressionAliasOutput(int index);

    void setTypeOutput(int index, Class clazz);

    void setOperatorOutput(int index, XdbOperator operator);

    void setAliasOutput(int index, String alias);

    void setExpressionOutput(int index, XdbExpression expression);

    void setExpressionAliasOutput(int index, String alias_expression);

    int getSizeOutput();
}
